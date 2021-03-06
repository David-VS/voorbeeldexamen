package be.ehb.voorbeeldexamen.controllers;

import be.ehb.voorbeeldexamen.dao.BodDao;
import be.ehb.voorbeeldexamen.dao.PersoonDao;
import be.ehb.voorbeeldexamen.dao.ProductDao;
import be.ehb.voorbeeldexamen.entities.Bod;
import be.ehb.voorbeeldexamen.entities.Persoon;
import be.ehb.voorbeeldexamen.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;

@Controller
public class MainController {

    private final ProductDao mProductDao;
    private final PersoonDao mPersoonDao;
    private final BodDao mBodDao;

    @Autowired
    public MainController(ProductDao mProductDao, PersoonDao mPersoonDao, BodDao mBodDao) {
        this.mProductDao = mProductDao;
        this.mPersoonDao = mPersoonDao;
        this.mBodDao = mBodDao;
    }

    @GetMapping("/products")
    @ResponseBody
    public Iterable<Product> giveAllProducts(){
        return mProductDao.findAll();
    }

    @PostMapping("/users/new")
    @ResponseBody
    public HttpStatus createUser(@RequestParam("email")String email,
                                 @RequestParam("name")String name){
        Persoon temp = new Persoon();
        temp.setEmail(email);
        temp.setNaam(name);

        mPersoonDao.save(temp);
        return HttpStatus.OK;
    }

    @PostMapping("/products/new")
    @ResponseBody
    public HttpStatus createProduct(@RequestParam("startprijs")double price,
                                 @RequestParam("name")String name,
                                    @RequestParam("owner")int id,
                                    @RequestParam("duedate")String duedate){

        if(mPersoonDao.existsById(id)) {
            Persoon owner = mPersoonDao.findById(id).get();
            LocalDateTime due_date = LocalDateTime.parse(duedate);

            Product temp = new Product();
            temp.setOwner(owner);
            temp.setNaam(name);
            temp.setStartprijs(price);
            temp.setEindtijd(due_date);

            mProductDao.save(temp);
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public Iterable<Product> giveAllProductsForId(@PathVariable("id")int id){
        if(mPersoonDao.existsById(id)) {
            Persoon owner = mPersoonDao.findById(id).get();
            return owner.getProducten();
        }
        return new ArrayList<Product>();
    }

    @PostMapping("/bids/new")
    @ResponseBody
    public HttpStatus createBid(@RequestParam("product")int productID,
                                    @RequestParam("user")int userID,
                                    @RequestParam("price")double price){

        if(mPersoonDao.existsById(userID)) {
            Persoon owner = mPersoonDao.findById(userID).get();
            if(mProductDao.existsById(productID)){
                Product product = mProductDao.findById(productID).get();

                ArrayList<Bod> bids = new ArrayList<>();
                mBodDao.findAllByProductId(product).forEach(bids::add);
                Bod lastBid = new Bod();

                if (!bids.isEmpty())
                    lastBid= bids.get(bids.size()-1); //laatste toegevoegd id == laatste hogere prijs

                Bod bid = new Bod();
                bid.setPersoonId(owner);
                bid.setProductId(product);
                bid.setPrijs(price);

                if ((bids.isEmpty() && price > product.getStartprijs())
                        || price > lastBid.getPrijs()){
                    mBodDao.save(bid);
                    return HttpStatus.ACCEPTED;
                }
            }
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }
}
