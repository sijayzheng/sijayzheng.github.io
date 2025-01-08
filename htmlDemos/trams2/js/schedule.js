function scheduleY(obj) {
    var scheY = {
        fulfill: obj.fulfill || 55,
        listAll: obj.listAll || 100,
        speed: obj.speed || 25,
        again: obj.again || true,
        bgColor: obj.bgColor || "#7d8e91",
        listColor: obj.listColor || "#2bd74c",
        scWidth: obj.scWidth || "300",
        scHeight: obj.scHeight || "25",
        scdom: obj.scdom || "scheduleY",
    }
    if ($("#"+scheY.scdom).length === 1) {
        $("#"+scheY.scdom).append('<div id="ylist'+scheY.scdom+'" class="yList"> <span id="ynum'+scheY.scdom+'" class="yNum"></span></div>');
        if (scheY.again) {
            $("#ylist"+scheY.scdom).css("height", "0");
        }
        $("#"+scheY.scdom).css({
            "background-color": scheY.bgColor,
            "width": scheY.scWidth + "px",
            "height": scheY.scHeight + "px",
        })
        $("#ylist"+scheY.scdom).css("background-color", scheY.listColor)
        var num = 0;
        var numAll = Math.round(scheY.fulfill / scheY.listAll * 100);
        var xNumAll = setInterval(function () {
            num++;
            $("#ynum"+scheY.scdom).html(num + "%")
            if (num == numAll) {
                clearInterval(xNumAll)
            }
        }, scheY.speed)

        $("#ylist"+scheY.scdom).animate({"height": numAll + "%"}, scheY.speed * numAll)
    }
}
function scheduleX(obj) {
    var scheX = {
        fulfill: obj.fulfill || 55,
        listAll: obj.listAll || 100,
        speed: obj.speed || 25,
        again: obj.again || true,
        bgColor: obj.bgColor || "#7d8e91",
        listColor: obj.listColor || "#2bd74c",
        scWidth: obj.scWidth || "300",
        scHeight: obj.scHeight || "25",
        scdom: obj.scdom || "scheduleX",
    }
    if ($("#"+scheX.scdom).length === 1) {
        $("#"+scheX.scdom).append('<div id="xlist'+scheX.scdom+'" class="xList"> <span id="xnum'+scheX.scdom+'" class="xNum"></span></div>');
        if (scheX.again) {
            $("#xlist"+scheX.scdom).css("width", "0");
        }
        $("#"+scheX.scdom).css({
            "background-color": scheX.bgColor,
            "width": scheX.scWidth + "px",
            "height": scheX.scHeight + "px",
        })
        $("#xlist"+scheX.scdom).css("background-color", scheX.listColor)
        var num = 0;
        var numAll = Math.round(scheX.fulfill / scheX.listAll * 100);
        var xNumAll = setInterval(function () {
            num++;
            $("#xnum"+scheX.scdom).html(num + "%")
            if (num == numAll) {
                clearInterval(xNumAll)
            }
        }, scheX.speed)

        $("#xlist"+scheX.scdom).animate({"width": numAll + "%"}, scheX.speed * numAll)
    }
}