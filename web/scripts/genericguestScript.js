/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


document.write('<style>.js_hidden { display: none; }</style>');
			
$(document).ready(function(){
    $("#absent").click(function(){
        $("#allergyBox").hide(150);
        $("#emailBox").hide(150);
    });
    
    if($("input[name=isAttending]").is(":checked")) {
        $("#allergyBox").show(150);
        $("#emailBox").show(150);
    }
    
    $("#attending").click(function(){
        $("#allergyBox").show(150);
        $("#emailBox").show(150);
    });

    $("input[type=submit]").attr("disabled", "disabled") // disable submit button upon page launch
        .css("background-color", "#8E8E8E")
        .css("border-color", "#717171")
        .css("cursor", "auto");

    $("input[type=text]").on("keyup", function(){checkRequiredFieldsForButton();});
    $("input[name=isAttending]").on("change", function(){checkRequiredFieldsForButton();});



    function checkRequiredFieldsForButton() {
        //if(($("#firstName").val() != "" )
        if(new RegExp("[A-Za-z\\s]+").test($("#firstName").val())
        && new RegExp("[A-Za-z\\s]+").test($("#lastName").val())
        && $("input[name=isAttending]").is(":checked")){ // if firstname and lastname are filled out and attending/absent is selected
            $("input[type=submit]").removeAttr("disabled") // enable submit button
                .css("background-color", "#811c1c")
                .css("border-color", "#470404"); 

                $("input[type=submit]").mouseenter(function(){ // mouse over button
                    $(this).css("background-color", "#FFFFFF")
                        .css("color", "#811c1c")
                        .css("cursor", "pointer");
                });

                $("input[type=submit]").mouseleave(function(){ // mouse not over button
                    $(this).css("background-color", "#811c1c")
                        .css("color", "#FFFFFF")
                        .css("cursor", "auto");
                });

        } else {
            $("input[type=submit]").attr("disabled", "disabled") // disable submit button
                .css("background-color", "#8E8E8E")
                .css("border-color", "#717171"); 
        }
    }
});