$(document).ready(function(){
    $("#add").click(function(){
        $("#addfri").animate({height:'toggle'});
    })
    $("#af").click(function(){
        $("#addfri").hide();
    })
})
window.onload=function(){
    var Left=document.getElementById("left");
    var aLi=Left.getElementsByTagName("li");
    var Mid_body=document.getElementById("mid_body");
    var aLi2=Mid_body.getElementsByTagName("li");
    var oRtp=document.getElementById("ri_top_p1");
    var oRight_img=document.getElementById("right_img");
    var oElse=document.getElementById("else");

    var arrp=['JaeHyun','JaeMin','RenJun','ChenLe'];
    var arrImg=['img/3.jpg','img/5.jpg','img/6.jpg','img/7.jpg'];
    
    
        aLi[2].className="change1";
        for(var i=0;i<aLi.length;i++){   
            aLi[i].index=i;
            aLi[i].onclick=function(){
                for(var i=0;i<aLi.length;i++){
                    aLi[i].className='';
                }
                this.className="change1";
            }
        }
    
        oRtp.innerHTML=arrp[1];
        oRight_img.src=arrImg[1];
        aLi2[1].className="change2";
        for(var i=0;i<aLi2.length;i++){   
            aLi2[i].index=i;
            aLi2[i].onclick=function(){
                oRtp.innerHTML=arrp[this.index];
                oRight_img.src=arrImg[this.index];
                for(var i=0;i<aLi2.length;i++){
                    aLi2[i].className='';
                }
                this.className="change2";
            }
        }
}
