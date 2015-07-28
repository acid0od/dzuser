var isShunk = false;
var guess;
var randomLoc = Math.floor(Math.random() * 5);

var scores = [60, 50, 60, 58, 54, 54, 58, 50, 52, 54, 48, 69, 34, 55, 51, 52, 44, 51, 69, 64, 66, 55, 52, 61, 46, 31, 57, 52, 44, 18, 41, 53, 55, 61, 51, 44];
function init () {
var output = document.getElementById("green");
output.innerHTML = "Red label";
}

window.onload = init;
/*
for (var i = 0 ; i < scores.length; i++) {
    output = "Buble solution #" + i + " score=" + scores[i] + "<br/>";
    document.write(output);
}
*/


/*
while (isShunk == false) {

    guess = prompt("Ready, aim fire.:" + randomLoc);

    if (guess == "q") {
        isShunk = true;
    }
}*/
