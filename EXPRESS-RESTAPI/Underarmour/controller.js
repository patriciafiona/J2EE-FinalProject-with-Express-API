'use strict';

var response = require('./res');
var connection = require('./conn');

exports.index = function(req, res) {
    response.ok("Hello from the Node JS RESTful side! This is API for Underarmour Apps...", res)
};

exports.users = function(req, res) {
    connection.query('SELECT * FROM users', function (error, rows, fields){
        if(error){
            console.log(error)
        } else{
            response.ok('users', rows, res)
        }
    });
};

exports.products = function(req, res) {
    connection.query('SELECT stocks.*, photos.file, photos.filename FROM stocks '+
                    'INNER JOIN photos ON stocks.id = photos.stock_id '+
                    'GROUP BY (stocks.id)', function (error, rows, fields){
        if(error){
            console.log(error)
        } else{
            response.ok('products', rows, res)
        }
    });
};

exports.photos = function(req, res) {
    connection.query('SELECT * FROM photos', function (error, rows, fields){
        if(error){
            console.log(error)
        } else{
            response.ok('photos', rows, res)
        }
    });
};

exports.photos_id = function(req, res) {
    connection.query('SELECT * FROM photos WHERE id = ? ', [req.params.id], function (error, rows, fields){
        if(error){
            console.log(error)
        } else{
            response.ok('photos_id', rows, res)
        }
    });
};

exports.photos_stockid = function(req, res) {
    connection.query('SELECT * FROM photos WHERE stock_id = ? ', [req.params.stock_id], function (error, rows, fields){
        if(error){
            console.log(error)
        } else{
            response.ok('photos_stockid',rows, res)
        }
    });
};

exports.photosDetail_stockid = function(req, res) {
    connection.query('SELECT * FROM photos WHERE stock_id = ? and id = ? ', [req.params.stock_id, req.params.id], function (error, rows, fields){
        if(error){
            console.log(error)
        } else{
            response.ok('photosDetail_stockid', rows, res)
        }
    });
};

//untuk bagian di home (shortcut 5 barang terbaru)
exports.homeShoes = function(req, res) {
    connection.query('SELECT stocks.id, stocks.name, stocks.price, photos.filename FROM stocks '+
                    'INNER JOIN photos ON stocks.id = photos.stock_id '+
                    "WHERE stocks.category_id = '1' "+
                    'GROUP BY (stocks.id) '+
                    'Order BY stocks.updated_at DESC '+
                    'Limit 5', function (error, rows, fields){
        if(error){
            console.log(error)
        } else{
            response.ok('homeShoes',rows, res)
        }
    });
};

exports.homeShorts = function(req, res) {
    connection.query('SELECT stocks.id, stocks.name, stocks.price, photos.filename FROM stocks '+
                    'INNER JOIN photos ON stocks.id = photos.stock_id '+
                    "WHERE stocks.category_id = '2' "+
                    'GROUP BY (stocks.id) '+
                    'Order BY stocks.updated_at DESC '+
                    'Limit 5', function (error, rows, fields){
        if(error){
            console.log(error)
        } else{
            response.ok('homeShorts',rows, res)
        }
    });
};

exports.homeTouserTights = function(req, res) {
    connection.query('SELECT stocks.id, stocks.name, stocks.price, photos.filename FROM stocks '+
                    'INNER JOIN photos ON stocks.id = photos.stock_id '+
                    "WHERE stocks.category_id = '8' "+
                    'GROUP BY (stocks.id) '+
                    'Order BY stocks.updated_at DESC '+
                    'Limit 5', function (error, rows, fields){
        if(error){
            console.log(error)
        } else{
            response.ok('homeTouser & Tights',rows, res)
        }
    });
};