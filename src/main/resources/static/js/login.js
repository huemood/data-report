
    $(function(){
    
  //创建标识符；
  var nBool=false;
  var pwdBool=false;
  
  
  //1.当用户名失焦的时候
  $('#user_name').blur(function(){
    // 代入标识符
    //console.log($('#name'));
    var value =$.trim($(this).val());
    
    
    if(!value){
      $('.msg_ok1').html('账号不能为空');
      $('.msg_ok1').css('color','#692F9D');
      return false;
    }else{
      $('.msg_ok1').html('');
      nBool=true;
    }
  })  
  
  
  
  //2.当登录密码失焦的时候
  $('#password').blur(function(){

    var value =$.trim($(this).val());
    
    
    if(!value){
      $('.msg_ok1').html('密码不能为空');
      $('.msg_ok1').css('color','#692F9D');
      return false;
    }else{
    
      // 3.验证密码格式必须是6-11位的字母数字和下划线
      //3.1验证值
      //var reg = /^[a-zA-Z][a-zA-Z_0-9][!@#$%^&*]{5,10}$/;
      var reg = /^[a-zA-Z_0-9!@#$%^&*()+|{}?><\-\]\[\/\\]{6,11}$/;
      // 3.3 判断
      if (reg.test(value)) {  
        $('.msg_ok1').html('');
        pwdBool=true; 
      } else {
        $('.msg_ok1').html('密码需在6位到11位之间');
        $('.msg_ok1').css('color','#692F9D');
        return false; 
      }
      
    }
  })

  
  
  
  //表单提交事件
  $('form').submit(function(){
  
    // 在进行一次验证
    $('#user_name').blur();
    $('#password').blur();


    // 验证用户名和密码是否正确
    if (nBool && pwdBool) {
      return true;//  提交
    }
    // 阻止表单提交
    return false;
  })
  function auto(){
     $('#ouchn').css('height',$(window).height()-188.6);
      $('#ouchnform').css({'margin-top':($(window).height()-188.6)*0.37});
      $('#ouchn>div').css('max-width',($(window).height()-188.6)/863*1146);
      $('input').css({'height':0.181*$('input[name="username"]').width()});
     if($('input[name="username"]').height()-10<30){
           $('#submit-controll,.input-controll').css('font-size',$('input[name="username"]').height()-10)
        }
  }

  auto();
 
  $(window).resize(function(){
      
      auto();
    });
  // alert()
})
