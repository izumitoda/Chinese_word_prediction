function autopredict() {
    var min_length = 0; // min caracters to display the autocomplete
    var keyword =document.getElementById("ip").value;
    var x=document.getElementById("predict_list");
    if(keyword.length==0)
        document.getElementById("predict_list").style.visibility="hidden";
    else {
        document.getElementById("predict_list").style.visibility="visible";
        $.ajax({
            type: "GET",
            url: "/pj/predict?str=" + keyword,
            success: function (data) {
                $("#predict_list").html(data);

            },
            error: function () {
                $("#predict_list").html("Error XMLHttpRequest");
            }
        });
    }
}

// set_item : this function will be executed when we select an item
function set_item() {
    // change input value
    var y=$(this);
    y.innerHTML+="jfdksa";
    var x=document.getElementById("ip");
    x.innerHTML=x.innerHTML+y.innerHTML;
}