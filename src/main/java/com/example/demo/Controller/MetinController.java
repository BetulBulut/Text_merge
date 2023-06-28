package com.example.demo.Controller;


import com.example.demo.Entity.Metin;
import com.example.demo.Entity.metinModel;
import com.example.demo.Merge;
import com.example.demo.Service.MetinService;
import com.example.demo.Service.MetinServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MetinController {


    @Autowired
    private MetinService metinService;
    List<String> metinList=new ArrayList<>();
    List<String> metinList2=new ArrayList<String>();
    String output;
    Metin sorgu= new Metin();
    Merge merge = new Merge();
    Metin cevap=new Metin();
    long time;


    @GetMapping("/")
    public String sayfaYukle(Model model)
    {
        metinModel metin= new metinModel();
        String sonuc="";


        model.addAttribute("metin",metin);
        model.addAttribute("sonuc",sonuc);

        return "metin";
    }


    @PostMapping ("/")
    public String ekle(@ModelAttribute("metin") metinModel metin,Model model)
    {
        if(metin.getCumle()!="")
        {
            metinList.add(metin.getCumle());
            metinList2.add(metin.getCumle());
        }



        metinModel metin2= new metinModel();
        model.addAttribute("metin",metin2);

        return "metin";
    }

    @GetMapping ("/metin/birlestir")
    public String birlestir( Model model)
    {

        sorgu.setInputs(metinList2);
        cevap = merge.MergeText(sorgu);


        String sonuc=cevap.getOutput()+"\n\n"+"Toplam Geçen Süre:"+cevap.getTime()+" nanosaniye";



        metinModel metin= new metinModel();
        model.addAttribute("metin",metin);
        model.addAttribute("sonuc",sonuc);






        return "metin";
    }
    @GetMapping("/metin/kaydet")
    public String Kaydet(Model model)
    {


        Metin kayıt=new Metin();

        kayıt.setMergable(cevap.isMergable());
        kayıt.setOutput(cevap.getOutput());
        kayıt.setTime(cevap.getTime());
        kayıt.setInputs(metinList);



        metinService.saveMetin(kayıt);
        output="";
        metinList2.clear();
        metinList.clear();


        metinModel metin= new metinModel();
        String cevap="";

        model.addAttribute("metin",metin);
        model.addAttribute("sonuc",cevap);



        return "metin";
    }

    @GetMapping("/metin/temizle")
    public String Temizle(Model model){

        //Bu metodu Birleştirdiğimiz metni kaydetmek istemezek statik değişkenleri temizlemek için yazdım
        //Ekleyip birleştirmek istemezsek de basıyoruz
        metinModel metin= new metinModel();
        String cevap="";

        model.addAttribute("metin",metin);
        model.addAttribute("sonuc",cevap);

        output="";
        metinList2.clear();
        metinList.clear();



        return "metin";
    }

}
