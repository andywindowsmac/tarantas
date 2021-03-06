package com.some.aktilek.tarantas;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Database {
    public static int productId = 2;
    public HashMap<String, Product> products = new HashMap<>();
    public static Database SHARED_INSTANCE = new Database();

    private Database() {
        initProduct();
    }

    public HashMap<String, Product> search(String searchName) {
        HashMap<String, Product> result = this.products;

        for(int inputIndex = 0; inputIndex < searchName.length(); inputIndex++) {
            // take substring from beginning to inputIndex
            // ex: pointName: hello inputIndex: 1
            // he
            String substring = searchName.substring(0, inputIndex + 1);

            result = this.searchProduct(substring, result);
        }

        return result;
    }

    private HashMap<String, Product> searchProduct(
            String productName, HashMap<String, Product> products
    ) {
        HashMap<String, Product> result = new HashMap<>();

        // run throw keys and find partially equal to substring
        for (String key: products.keySet()) {
            if (!key.toLowerCase().contains(productName.toLowerCase())) continue;

            // points has pointName
            // add to our hash map
            result.put(key, this.products.get(key));
        }

        // return result with elements which contains our pointName
        return result;
    }

    private void initProduct() {
        this.products.put("Компрессор кондиционера для Toyota Camry, Corolla, Land Cruiser, Yaris", new Product(
                "-1",
                "Компрессор кондиционера для Toyota Camry, Corolla, Land Cruiser, Yaris",
                "Компрессора кондиционера в наличии, находятся в Астане. Отправка по регионам входит в стримость компрессора. Отправляем транспортной компанией курьером до дома. Приглашаем к сотрудничеству! \n" +
                "Пришлите нам номер кузова или фото техпаспорта на Whatsupp.\n" +
                "Наш сайт www.acauto.kz\n" +
                "Б/У привезенные из Японии, а так же новые дубликаты из ОАЭ.\n" +
                "Audi A3, A4, A6, A8\n" +
                "BMW 3, 5, 7, X5, X6\n" +
                "Volvo Xc90\n" +
                "Infinity EX35, EX37, FX35, FX37, FX50, G25, G35, G37, M35, QX70, Q50\n" +
                "Kia Carnival, Sedona, Sorento, Sportage, Optima, Picanto, Rio, Cerato.\n" +
                "Lexus Rx330, Rx350, Gs300, Lx470, Lx570, Is250, Es350, Ls460.\n" +
                "Mazda 3, 6.\n" +
                "Land Rover Range Rover Sport, Range Rover, Freelander.\n" +
                "Mercedes-Benz GL, ML, A, S, C, E, R - Class.\n" +
                "Mitsubishi Lancer, Outlander, ASX.\n" +
                "Nissan Qashqai, Juke, Micra, Patrol, Primera\n" +
                "Peugeot 207\n" +
                "Porsche Cayenne\n" +
                "Subaru Legasy, Outback, Forster.\n" +
                "Toyota Avalon, Auris, Venza, Camry 40, Camry 50, Corolla, Land Cruiser, Land Cruiser Prado, Rav4, Yaris.\n" +
                "Hyundai IX35, Santa Fe, Tucson.\n" +
                "Skoda Octavia, Rapid\n" +
                "И многие многие другие!",
                "https://olxkz-ringkz03.akamaized.net/images_slandokz/240306780_1_644x461_kompressor-konditsionera-dlya-toyota-camry-corolla-land-cruiser-yaris-almaty.jpg",
                50000,
                10
        ));

        this.products.put("Двигатель (мотор) toyota carina e", new Product(
                "-1",
                "Двигатель (мотор) toyota carina e",
                "4афе привозной двигатель цена за голый двигатель",
                "https://olxkz-ringkz01.akamaized.net/images_slandokz/207145656_1_1000x700_dvigatel-motor-toyota-carina-e-almaty.jpg",
                150000,
                5
        ));
    }

    public Product getPointByKey(String key) {
        return this.products.get(key);
    }

    /* Product API */
    /* Add product api */
    public void postProduct(String title, String description,String imageUrl, double price,int count) {
        String id = productId + "";
        productId++;
        this.products.put(title, new Product(id, title, description, imageUrl, price, count));
    }
}
