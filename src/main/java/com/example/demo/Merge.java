package com.example.demo;

import com.example.demo.Entity.Metin;

import java.util.*;

public class Merge {



    public Merge() {
    }



    public Metin MergeText(Metin metin){
        long startTime = System.nanoTime();
        List<String> Metinler;
        Metinler = metin.getInputs();
        String output="";
        //Kelime mi yoksa cumle veya paragraf mi diye bakiyoruz.
        if(Metinler.get(0).contains(" ") || Metinler.get(0).contains(".")){

            char[] cumle = Metinler.get(0).toCharArray();
            int noktaSayisi=0;
            for(int i=0;i<Metinler.get(0).length();i++){
                if(cumle[i] == '.'){
                    noktaSayisi++;
                }
            }

            if(noktaSayisi>1){

                output= Paragraf(Metinler);


            }else{
                output = Cumle(Metinler);


            }
        }else{

            output=Kelime(Metinler);
        }
        long endTime =System.nanoTime();
        long duration = (endTime - startTime);




        metin.setOutput(output);
        if(output=="Birleştirilemedi"){
            metin.setMergable(false);
        }else{
            metin.setMergable(true);
        }

        metin.setTime(duration);




        return metin;
    }

    public String Paragraf(List<String> paragraflar){
        //cumle bazli arama karsilastirma yapiyoruz.


        String[] Temp;
        String output="Birleştirilemedi";



        for(int i =0;i< paragraflar.size();i++){//paragraf sayisi kadar donecek




        for (int y= i+1;y<paragraflar.size();y++){

            if(paragraflar.get(y) == output){//paragraflar karışık verildiyse devreye girecek
                String tmp=paragraflar.get(y);
                paragraflar.set(y,paragraflar.get(i));
                paragraflar.set(i,tmp);

            }


            Temp= paragraflar.get(i).split("\\.");

            String[] words = paragraflar.get(y).split("\\.");
            List<String> paragraf = new ArrayList<>();
            Collections.addAll(paragraf, words);





                for (int j =0;j<Temp.length;j++) {//paragrafin cumleleri icinde geziyoruz



                for(int x=0;x<paragraf.size();x++){


                    if(paragraf.get(x).contains(Temp[j]) || Temp[j].contains(paragraf.get(x))){//diğer paragraflarla ortak cümle buluyor mu



                        output="";
                        for(int b =0;b<j;b++){

                                output+=Temp[b]+".";//ortak cümleden öncesini yazıyoruz

                        }


                        for(int b =0;b<x;b++){

                            if(j== Temp.length-1){//ortak eleman ilk paragrafın  son elemanı mı
                                output+=paragraf.get(b)+"."; //ortak cümleden öncesini yazıyoruz
                            }else if(j< Temp.length-1){//son eleman değil
                                for(int s=j+1;s<Temp.length;s++){
                                    if(!Temp[s].contains(paragraf.get(b)))
                                        output+=paragraf.get(b)+"."; //ortak cümleden öncesini yazıyoruz
                                }
                            }

                        }



                        if(Temp[j].contains(paragraf.get(x))){

                                output+=Temp[j]+".";

                        }else if(paragraf.get(x).contains(Temp[j])){//ortak cümleyi yazıyoruz

                                output+=paragraf.get(x)+".";
                        }


                        for(int b =x+1;b<paragraf.size();b++){
                            output+=paragraf.get(b)+"."; //ortak cümleden sonrasını yazıyoruz
                        }


                        for(int b =j+1;b<Temp.length;b++){

                                if(!output.contains(Temp[b])){
                                    output+=Temp[b]+".";
                                }
                                //ortak cümleden sonrasını yazıyoruz

                        }



                        //Bu paragrafı yazmış oluyoruz sonrasına geçiyoruz.
                        x=paragraf.size();
                        paragraflar.set(y,output);//Bir sonraki paragrafı var olan çıktımızla değiştiiyoruz. ip yumağı gibi büyüyerek gidecek.
                        y=paragraflar.size();
                        j=Temp.length;

                    }
                }
                }
            }
        }

        return output;
    }
    public String Cumle(List<String> cumleler){

        String[] Temp;
        String output="Birleştirilemedi";



        for(int i =0;i< cumleler.size();i++){//cümle sayisi kadar donecek





            for (int y= i+1;y<cumleler.size();y++){//cümleler karışık verildiyse burası çalışacak

                    if(cumleler.get(y) == output){
                        String tmp=cumleler.get(y);
                        cumleler.set(y,cumleler.get(i));
                        cumleler.set(i,tmp);

                    }

                Temp= cumleler.get(i).trim().replaceAll("\\.", "").split("\\s+");

                String[] words = cumleler.get(y).trim().replaceAll("\\.", "").split("\\s+");
                List<String> cumle = new ArrayList<>();
                Collections.addAll(cumle, words);

            for (int j =0;j<Temp.length;j++) {//cümlenin kelimeleri icinde geziyoruz


                    for(int x=0;x<cumle.size();x++){






                        if(cumle.get(x).contains(Temp[j]) || Temp[j].contains(cumle.get(x))){//diğer cümlelerle ortak kelime buluyor mu



                            output="";
                            for(int b =0;b<j;b++){

                                output+=Temp[b]+" ";//ortak kelimeden öncesini yazıyoruz

                            }



                            for(int b =0;b<x;b++){

                                if(j== Temp.length-1){//ortak eleman ilk cümlenin  son elemanı mı
                                    output+=cumle.get(b)+" "; //ortak kelimeden öncesini yazıyoruz

                                }else if(j< Temp.length-1){//son eleman değil

                                    for(int s=j+1;s<Temp.length;s++){
                                        if(!Temp[s].contains(cumle.get(b)))
                                            output+=cumle.get(b)+" "; //ortak kelimeden öncesini yazıyoruz
                                    }
                                }

                            }


                            if(Temp[j].contains(cumle.get(x))){

                                output+=Temp[j]+" ";

                            }else if(cumle.get(x).contains(Temp[j])){//ortak kelimeyi yazıyoruz

                                output+=cumle.get(x)+" ";
                            }



                            for(int b =x+1;b<cumle.size();b++){
                                output+=cumle.get(b)+" "; //ortak kelimeden sonrasını yazıyoruz
                            }



                            for(int b =j+1;b<Temp.length;b++){

                                if(!output.contains(Temp[b])){
                                    output+=Temp[b]+" ";
                                }
                                //ortak kelimeden sonrasını yazıyoruz

                            }




                            //Bu cümleyi yazmış oluyoruz sonrasına geçiyoruz.
                            x=cumle.size();
                            cumleler.set(y,output);//Bir sonraki cümleyi var olan çıktımızla değiştiiyoruz. ip yumağı gibi büyüyerek gidecek.
                            y=cumleler.size();
                            j=Temp.length;

                        }
                    }
                }
            }
        }





        return output;
    }
    public String Kelime(List<String> kelimeler){


        String output="Birleştirilemedi";



        for(int i =0;i< kelimeler.size();i++){//kelimeleri geziyoruz



            for(int j=0;j<kelimeler.get(i).length();j++)
            {

                for(int m=0;m<kelimeler.get(i).length()-j;m++)
                {
                    String kelime=kelimeler.get(i).substring(j,kelimeler.get(i).length()-m);//substringlerini buluyoruz

                    if(kelime.length()>1){// art arda en az iki harf ortaksa kelimeleri birleştiriyor
                        for(int y=i+1;y<kelimeler.size();y++){//diğer kelimeleri geziyoruz
                            if(kelimeler.get(y).contains(kelime)){


                                int index = kelimeler.get(y).indexOf(kelime);

                                output="";

                                output+=kelimeler.get(i).substring(0,j) + kelimeler.get(y).substring(0,index)
                                        +kelime+kelimeler.get(i).substring(j +kelime.length())
                                        +kelimeler.get(y).substring(index+kelime.length());


                                kelimeler.set(y,output);
                                y= kelimeler.size();
                                m=kelimeler.get(i).length()-j;
                                j=kelimeler.get(i).length();

                            }
                        }
                    }
                }
            }

        }

        return output;
    }
}
