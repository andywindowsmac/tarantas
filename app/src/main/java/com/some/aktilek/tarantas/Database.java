package com.some.aktilek.tarantas;

import java.util.HashMap;

public class Database {
    public HashMap<String, Product> products = new HashMap<>();

    Database() {
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
                "0",
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
                50000
        ));

        this.products.put("Двигатель (мотор) toyota carina e", new Product(
                "0",
                "Двигатель (мотор) toyota carina e",
                "4афе привозной двигатель цена за голый двигатель",
                "https://olxkz-ringkz01.akamaized.net/images_slandokz/207145656_1_1000x700_dvigatel-motor-toyota-carina-e-almaty.jpg",
                150000
        ));
    }

    public Product getPointByKey(String key) {
        return this.products.get(key);
    }
}
