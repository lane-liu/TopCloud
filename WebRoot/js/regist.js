$(function() {
			$("form :input").blur(function() {
					var $parent = $(this).parent();
					$parent.find(".msg").remove(); //删除以前的提醒元素（find()：查找匹配元素集中元素的所有匹配元素）

					//验证用户名
					if($(this).is("#name")) {
						var nameVal = $.trim(this.value);
						var regName = /[~#^$@%&!*()<>:;'"{}【】  ]/;
						if(nameVal == "" || nameVal.length < 2 || regName.test(nameVal)) {
							var errorMsg = " 用户名非空，长度2-20位，不包含特殊字符！";
							$parent.append("<span class='msg onError'>" + errorMsg + "</span>");
						} else {
							var okMsg = " 输入正确";
							$parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
						}
					}
					//验证手机号
					if($(this).is("#email")) {
						var emailVal = $.trim(this.value);
						var regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
						if(emailVal == "" || !regEmail.test(emailVal)) {
							var errorMsg = " 请输入正确邮箱！";
							$parent.append("<span class='msg onError'>" + errorMsg + "</span>");
						} else {
							var okMsg = " 输入正确";
							$parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
						}
					}
					//验证密码
					if($(this).is("#psd")) {
						var psdVal = $.trim(this.value);
						var regPsd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
						if(psdVal == "" || !regPsd.test(psdVal)) {
							var errorMsg = " 密码为6-20位字母、数字的组合！";
							$parent.append("<span class='msg onError'>" + errorMsg + "</span>");
						} else {
							var okMsg = " 输入正确";
							$parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
						}
					}

					//验证确认密码
					if($(this).is("#repsd")) {
						var psdVal = $("#psd").val();
						var repsdVal = $.trim(this.value);
						if(repsdVal == "") {
							var errorMsg = " 请确认您的密码";
							$parent.append("<span class='msg onError'>" + errorMsg + "</span>");
						} else {
							if(repsdVal != psdVal) {
								var errorMsg = " 您输入密码前后不一致";
								$parent.append("<span class='msg onError'>" + errorMsg + "</span>");
							} else {
								var okMsg = " 输入正确";
								$parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
							}
						}
					}

					//验证验证码
					if($(this).is("#code")) {
						var code = $.trim(this.value);
						//异步发送AJAX请求
						$.get("/dang/verifyCode.do?verifyCode=" + verifyCode, function(data) {
								if(!data || code == "") {
									var errorMsg = " 请输入图片中的四个字母。";
									$parent.append("<span class='msg onError'>" + errorMsg + "</span>");
								} else {
									var okMsg = " 输入正确";
									$parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
								}
							})
						}

					}).keyup(function() {
					//triggerHandler 防止事件执行完后，浏览器自动为标签获得焦点
					$(this).triggerHandler("blur");
				}).focus(function() {
					$(this).triggerHandler("blur");
				});

				//点击重置按钮时，通过trigger()来触发文本框的失去焦点事件
				$("#btnSubmit").click(function() {
					//trigger 事件执行完后，浏览器会为submit按钮获得焦点
					$("form .required:input").trigger("blur");
					var numError = $("form .onError").length;
					if(numError) {
						return false;
					}
					alert('注册成功！')
				});
			})







		//为表单的必填文本框添加提示信息（选择form中的所有后代input元素）
		// $("form :input.required").each(function () {
		//     //通过jquery api：$("HTML字符串") 创建jquery对象
		//     var $required = $("<strong class='high'>*</strong>");
		//     //添加到this对象的父级对象下
		//     $(this).parent().append($required);
		// });
		// var errorMsg = $(".error-msg").text();
		//为表单元素添加失去焦点事件
		//验证确认密码
		//          if($(this).is("#repsd")){
		//              var repsdVal = $.trim(this.value);
		//              var regRepsd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
		//              if(repsdVal== "" || !regRepsd.test(repsdVal)){
		//                  var errorMsg = " 请确认您的密码";
		//                  $parent.append("<span class='msg onError'>" + errorMsg + "</span>");
		//              }
		//              else{
		//                  var okMsg=" 输入正确";
		//                  $parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
		//              }
		//          }

		//<script src="../assets/libs/particles/js/lib/stats.js"></script> 
		//	<script>
		//		var count_particles, stats, update;
		//		stats = new Stats;
		//		stats.setMode(0);
		//		stats.domElement.style.position = 'absolute';
		//		stats.domElement.style.left = '0px';
		//		stats.domElement.style.top = '0px';
		//		document.body.appendChild(stats.domElement);
		//		count_particles = document.querySelector('.js-count-particles');
		//		update = function() {
		//			stats.begin();
		//			stats.end();
		//			if (window.pJSDom[0].pJS.particles && window.pJSDom[0].pJS.particles.array) {
		//				count_particles.innerText = window.pJSDom[0].pJS.particles.array.length;
		//			}
		//			requestAnimationFrame(update);
		//		};
		//		requestAnimationFrame(update);
		//	</script>
		//	
		//	<script>
		//		$(".download_btn").click(function(){
		//			if($(".QRcode").css("display")=="none"){
		//				$(".QRcode").show();
		//				$(".download_btn").text("关闭二维码");
		//			}else{
		//				$(".QRcode").hide();
		//				$(".download_btn").text("下载知乎App");
		//			}
		//		});	
		//	</script>

		//		if $("#repsd").blur(function(){
		//		var pwd=$("#psd").val();
		//		var repwd=$("#repsd").val();
		//			if(repwd == "" ) {
		//				var errorMsg = " 请确认您的密码";
		//				$parent.append("<span class='msg onError'>" + errorMsg + "</span>");
		//			} else {
		//				if(repwd != pwd){
		//				var errorMsg = " 您输入的密码不一样";
		//				$parent.append("<span class='msg onError'>" + errorMsg + "</span>");
		//				}else{
		//				var okMsg = " 输入正确";
		//				$parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");					
		//				}
		//			}
		//		}