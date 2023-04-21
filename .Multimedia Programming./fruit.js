/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//define the arry
var fruit = [
    {name: "Apple", quantity: 20, color: "red"},
    {name: "Orange", quantity: 10, color: "orange"},
    {name: "Banana", quantity: 15, color: "yellow"},
    {name: "Kiwi", quantity: 5, color: "green"},
    {name: "Blueberry", quantity: 5, color: "blue"},
    {name: "Grapes", quantity: 10, color: "purple"}
];

//use the for loop get each element to draw the fruit
function drawfruit() {
    var canves = document.getElementById("mycanvas");
    var ctx = canves.getContext("2d");
    
    //for loop
    for (let i = 0; i <= 6; i++) {
        //define the rect value
        const rectlength = fruit[i].quantity * 50;
        const rectwidth = 150;
        
        //define the rect position
        const rect_x = 0;
        const rect_y = 150 * i;
        
        //define the content position
        const fruitname_x = 10;
        const fruitname_y = 100 + 150 * i;
        const fruitqty_x = 50;
        const fruitqty_y = 50 + 150 * i;
        
        //fill the rect color and draw rect for each fruit
        ctx.fillStyle = fruit[i].color;
        ctx.fillRect(rect_x, rect_y, rectlength, rectwidth);
        
        //dill the content
        ctx.font = "bold 30px Arial";
        ctx.fillStyle = "Black";
        ctx.fillText(fruit[i].quantity, fruitqty_x, fruitqty_y);
        ctx.fillText(fruit[i].name, fruitname_x, fruitname_y);
        
    }
}

document.addEventListener('DOMContentLoaded', drawfruit);


