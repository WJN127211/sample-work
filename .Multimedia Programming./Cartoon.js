/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//this funcation we use to change the background color
function changeCanvasColor() {
    // Locate the element "mycanvas" in the document.
    var canvas = document.getElementById("mycanvas");
    var context = canvas.getContext("2d");

    //set the color
    context.fillStyle = "lightcyan";

    //set the color range
    context.fillRect(0, 0, canvas.width, canvas.height);
}

//draw the sun and fill the color
function drawsun() {
    // Locate the element "mycanvas" in the document.
    var canvas = document.getElementById('mycanvas');
    var ctx = canvas.getContext('2d');

    //draw the sun
    //Draw a rectangle and fill with a radial/circular gradient:
    var radgrad = ctx.createRadialGradient(100, 100, 20, 100, 100, 50);

    //define thcolor
    radgrad.addColorStop(0, 'red');
    radgrad.addColorStop(0.3, 'yellow');
    radgrad.addColorStop(1, 'lightcyan');

    ctx.fillStyle = radgrad;
    ctx.fillRect(50, 50, 150, 150);
}

//draw mountain
function drawmountain() {
    // Locate the element "mycanvas" in the document.
    var canves = document.getElementById("mycanvas");
    var ctx = canves.getContext("2d");
    
    ctx.beginPath();
    ctx.moveTo(0, 580);           // Create a starting point
    ctx.lineTo(50, 580);          // Create a horizontal line
    
    //Draw a cubic Bézier curve:
    ctx.bezierCurveTo(50, 380, 500, 380, 500, 580);
    ctx.lineCap = "round";
    
    //draw the line for gross
    ctx.moveTo(500, 580);
    ctx.lineTo(800, 580);
    
    //fill the color;
    ctx.fillStyle = "green";
    ctx.fill();
    ctx.stroke();

}

//draw the house
function drawhouse() {
    // Locate the element "mycanvas" in the document.
    var canves = document.getElementById("mycanvas");
    var ctx = canves.getContext("2d");
    
    //begin to draw
    ctx.beginPath();
    
    //draw a rect
    ctx.rect(200, 300, 100, 150);
    
    //fill the color
    ctx.fillStyle = "beige";
    ctx.fill();
    ctx.stroke();


}

//draw the roof
function drawroof() {
    // Locate the element "mycanvas" in the document.
    var canves = document.getElementById("mycanvas");
    var ctx = canves.getContext("2d");
    
    ctx.beginPath();
    //use the linejoin to draw the roof
    ctx.lineJoin = "round";
    ctx.moveTo(180, 325);
    ctx.lineTo(250, 240);
    ctx.lineTo(320, 325);
    
    //fill the color
    ctx.fillStyle = "gray";
    ctx.fill();
    ctx.stroke();
}


//draw the door
function drawdoor() {
    // Locate the element "mycanvas" in the document.
    var canves = document.getElementById("mycanvas");
    var ctx = canves.getContext("2d");
    
    //draw the rect
    ctx.beginPath();
    ctx.rect(230, 400, 20, 30);
    ctx.fillStyle = "brown";
    ctx.fill();
    ctx.stroke();
}


//draw the window
function drawwindow() {
    // Locate the element "mycanvas" in the document.
    var canves = document.getElementById("mycanvas");
    var ctx = canves.getContext("2d");
    
    //draw two small rect as wndow
    ctx.beginPath();
    ctx.rect(208, 340, 15, 15);
    ctx.rect(270, 380, 15, 15);
    ctx.fillStyle = "white";
    ctx.fill();
    ctx.stroke();
}

//draw chiney
function drawchimney() {
    // Locate the element "mycanvas" in the document.
    var canves = document.getElementById("mycanvas");
    var ctx = canves.getContext("2d");
    
    //draw rect
    ctx.beginPath();
    ctx.rect(280, 260, 10, 25);
    //fill the color
    ctx.fillStyle = "red";
    ctx.fill();
    ctx.stroke();
}


//write some notes on the canves
function writecapitiontext() {
    // Locate the element "mycanvas" in the document.
    var canves = document.getElementById("mycanvas");
    var ctx = canves.getContext("2d");
    ctx.font = "20px Arial";
    ctx.fillText("My First Project---Cartoon", 550, 20);
}


//use the for loop to draw the grass
function drawgrass() {
    // Locate the element "mycanvas" in the document.
    var canves = document.getElementById("mycanvas");
    var ctx = canves.getContext("2d");
    
    //fill the color
    ctx.fillStyle = "green";
    for (let i = 0; i <= 12; i++) {
        //define the coordinate
        const x1 = 510 + i * 23;
        const x2 = 520 + i * 23;
        const x3 = 530 + i * 23;
        const y1 = 570;
        const y2 = 560;
        const y3 = 580;
        
        //draw the gross
        ctx.moveTo(x2, y3);
        ctx.lineTo(x2, y1);
        ctx.arcTo(x1, 570, x1, 560, 7);
        ctx.arcTo(x2, 560, x2, 570, 7);
        ctx.arcTo(x2, 560, x3, 560, 7);
        ctx.arcTo(x3, 570, x2, 570, 7);
        
        ctx.fill();
        ctx.stroke();
    }

}

//draw the ground
function drawground() {
    // Locate the element "mycanvas" in the document.
    var canves = document.getElementById("mycanvas");
    var ctx = canves.getContext("2d");
    
    //draw the rect
    ctx.rect(0, 550, 800, 50);
    ctx.stroke();

    //fill the color
    ctx.fillStyle = "gray";
    ctx.fillRect(0, 550, 800, 50);
}

//draw the cloud use the for loop
function drawcloud() {
    // Locate the element "mycanvas" in the document.
    var canves = document.getElementById("mycanvas");
    var ctx = canves.getContext("2d");
    
    //fill the color
    ctx.fillStyle = "white";
    for (let i = 0; i <= 2; i++) {
        //define the coordinate
        const x1 = 200 + 250 * i;
        const x2 = 220 + 250 * i;
        const x3 = 240 + 250 * i;
        
        //draw cloud
        ctx.moveTo(x3, 220);
        ctx.lineTo(x1, 220);
        ctx.arc(x1, 200, 20, 0.5 * Math.PI, 1.5 * Math.PI);
        ctx.arc(x2, 180, 20, 1 * Math.PI, 0 * Math.PI);
        ctx.arc(x3, 200, 20, 1.5 * Math.PI, 0.5 * Math.PI);
        
        ctx.fill();
        ctx.stroke();
    }
}

//attaches an event handler to the specified element.
document.addEventListener('DOMContentLoaded', changeCanvasColor);
document.addEventListener('DOMContentLoaded', drawsun);
document.addEventListener('DOMContentLoaded', drawground);
document.addEventListener('DOMContentLoaded', drawhouse);
document.addEventListener('DOMContentLoaded', drawchimney);
document.addEventListener('DOMContentLoaded', drawroof);
document.addEventListener('DOMContentLoaded', drawdoor);
document.addEventListener('DOMContentLoaded', drawwindow);
document.addEventListener('DOMContentLoaded', drawcloud);
document.addEventListener('DOMContentLoaded', drawmountain);
document.addEventListener('DOMContentLoaded', writecapitiontext);
document.addEventListener('DOMContentLoaded', drawgrass);




