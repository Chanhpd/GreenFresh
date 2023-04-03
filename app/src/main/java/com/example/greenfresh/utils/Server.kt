package com.example.greenfresh.utils

class Server {
    companion object {
        // IP address
        val localhost = "192.168.1.13"
        //

        val linkLogin = "http://$localhost/greenfresh/login.php"
        val linkRegister = "http://$localhost/greenfresh/signup.php"

        val linkBanner = "http://$localhost/greenfresh/banner.php"
        val linkCategory="http://$localhost/greenfresh/category.php"
        val linkSeller="http://$localhost/greenfresh/getProductSeller.php"
        val linkProduct="http://$localhost/greenfresh/product.php"
    }
}