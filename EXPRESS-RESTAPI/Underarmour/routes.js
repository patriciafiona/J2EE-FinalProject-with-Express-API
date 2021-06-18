'use strict';

module.exports = function(app) {
    var todoList = require('./controller');

    var express = require('express');
    app.use(express.static('public'));  //get photos in public folder

    app.route('/')
        .get(todoList.index);

    app.route('/users')
        .get(todoList.users);

    app.route('/products')
        .get(todoList.products);

    app.route('/photos')
        .get(todoList.photos);
    
    app.route('/photos/:id')
        .get(todoList.photos_id);
    
    app.route('/stocksPhotos/:stock_id')
        .get(todoList.photos_stockid);
    
    app.route('/stocksPhotos/:stock_id/:id')
        .get(todoList.photosDetail_stockid);
    
    //Bagian Halaman Home
    app.route('/home/shoes')
        .get(todoList.homeShoes);

    app.route('/home/shorts')
        .get(todoList.homeShorts);

    app.route('/home/touserTights')
        .get(todoList.homeTouserTights);
};