package com.dngwjy.algocargo.data

class QuestionGenerator {
    companion object{
        private val parameter:Array<String> = arrayOf("x","y","z")
         var mass=0
        fun generate():String{
            var soal=""
            val kg=(1..9).random()
            val rndm=(0..2).random()
            mass=rndm
            if(parameter[rndm] == "x"){
                soal="Kota Z memiliki 1 Buah Pelabuhan Induk yang besar dalam melakukan loading cargo dari dermaga, Crane dapat Menaikan keatas kapal Cargo besar secara" +
                        " Langsung, setiap kapal Cargo besar hanya dapat memuat sebanyak 60 cargo,\n Tentukan total beban kapal dengan massa cargo sebesar ${kg} Kg ${parameter[rndm]}$kg"
            }else if(parameter[rndm] == "y"){
                 soal="Kota Z memiliki 1 Buah Pelabuhan Induk yang terbatas spacenya, dalam melakukan loading cargo dari dermaga tidak bisa langsung dinaikan keatas kapal Cargo yang besar menggunakan" +
                         "dan Crane berada dilepas pantai,\nsehingga Cargo harus di angkut dari dermaga ke lepas pantai menggunakan kapal kecil satu persatu, dikarenakan aktifitas " +
                         "Pelabuhan sangat padat dan terbatas maka hanya ada satu jalur yang bisa dilalui oleh kapal pengangkut tersebut,\nsetiap kapal Cargo besar hanya dapat memuat sebanyak 60 cargo, Tentukan total beban kapal dengan massa Cargo sebesar ${kg} Kg, dengan melakukan checking kapasitas" +
                         " kapal Cargo Besar sebelum kapal kecil mengirimkan Cargo ke lepas pantai ${parameter[rndm]}$kg"
            }else {
                soal="Kota Z memiliki 1 Buah Pelabuhan Induk yang terbatas spacenya, dalam melakukan loading cargo dari dermaga tidak bisa langsung dinaikan keatas kapal Cargo yang besar menggunakan" +
                        "dan Crane berada dilepas pantai,\nsehingga Cargo harus di angkut dari dermaga ke lepas pantai menggunakan kapal kecil satu persatu, dikarenakan aktifitas " +
                        "Pelabuhan sangat padat dan terbatas maka hanya ada satu jalur yang bisa dilalui oleh kapal pengangkut tersebut,\nsetiap kapal Cargo besar hanya dapat memuat sebanyak 60 cargo, Tentukan total beban kapal dengan massa Cargo sebesar ${kg} Kg, dengan melakukan checking kapasitas" +
                        " kapal Cargo Besar setelah kapal kecil mengirimkan Cargo ke lepas pantai ${parameter[rndm]}$kg"
            }
            return soal
        }
    }
}