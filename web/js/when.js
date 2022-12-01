function whens(){
    var data = new Date();
    var hours = data.getHours();
    var text;
    if (hours >= 0 && hours <= 10) {
        text = `早上好!`;
    } else if (hours > 10 && hours <= 14) {
        text = `中午好!`;
    } else if (hours > 14 && hours <= 18) {
        text = `下午好!`;
    } else if (hours > 18 && hours <= 24) {
        text = `晚上好!`;
    }
    document.getElementById("isWhens").innerHTML = text;
    document.getElementById("isWhen").innerHTML = text;
};

setInterval("whens()", 1000);