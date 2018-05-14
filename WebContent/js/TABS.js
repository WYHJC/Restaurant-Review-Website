	function timestampToTime(timestamp) {
			if(timestamp=='')
				return '';
		    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
		    Y = date.getFullYear() + '-';
		    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
		    D = date.getDate() + ' ';
		    h = date.getHours() ;
		    if(h<10)
		    	h='0'+h;
		    m = date.getMinutes() ;
		    if(m<10)
		    	m='0'+m;
		    s = date.getSeconds();
		    if(s<10)
		    	s='0'+s;
		    return Y+M+D+h+':'+m+':'+s;
		};

(function($){
	$.fn.tabs = function(options){
		var def = {
			range: 100
		}
		var options = $.extend({},def,options)
		el = $(this)
		var chatSocket
		var chatList = new Array()
		// 获取刷新的元素
		function resfEl(){
			//alert(1);
			contLi = el.find('.cont-scroll li') // 标签
			contScr = el.find('.cont-scroll') // 标签父容器
			contLiActive = el.find('.cont-scroll .active') // 标签内的active

			wrapCont = el.find('.wrap-content') // 内容父容器
			contIndex = el.find('.wrap-content .cont-index') // 标签对应的内容容器
			contDel = el.find('.cont-scroll li .del') // 标签内的删除
			

			 
		
			//发送
			sendButton = el.find('.wrap-input botton')
			sendContent = el.find('.wrap-input input')


			

			//tab-left
			tabLeft = el.find('.tab-left')
			tabRight = el.find('.tab-right')
			tabDel = el.find('.tab-del')
		}
		resfEl()

		// 点击标签页
		function enActive(){
		contScr.on('click','li',function(){
			resfEl()
			var _this = $(this)
			_this
				.addClass('active')
				.siblings(_this)
				.removeClass('active')

			contIndex.eq(_this.index()).show().siblings().hide()
			//alert(_this.index())
		})

		contDel.on('click',function(){
			resfEl()
			// 如果在当前标签下 点击删除 ， 那么就在后面哪个添加active
			console.log(contLiActive.index())
			// 如果在最后一个标签下 点击删除 ， 那么就在倒数第二个添加active
			contLiActive.index() == $(this).parent().index() ? (contLiActive.index() ==  contLi.length-1 ? contLi.eq(contLi.length-2).addClass('active') : contLi.eq(contLiActive.index() + 1).addClass('active') ): ''
			 
			
			var _this = $(this)
			parLi = _this.parent().index()
			
			chatList.splice(parLi,1)
			
			//var idstr =_this.parent().attr("id");
				//idstr =idstr.substring(5)
			
			//removeid(idstr)
			//alert(parLi)
			_this.parent().remove()//remove
			contIndex.eq(parLi).remove()//remove
			
			resfEl()
			contIndex.eq(contLiActive.index()).show()
		})
		
		
		

	


		var moveIndex = 0;
		// 标签左滑
		tabLeft.on('click',function(){
			moveIndex < 0 ? moveIndex++ : moveIndex = 0
			contScr.stop().animate({'marginLeft':moveIndex*options.range})
		})

		// 标签右滑
		tabRight.on('click',function(){
			-(contScr.css('width').slice(0,-2)-1000) < contScr.css('margin-left').slice(0,-2) ? moveIndex-- : ''
			contScr.stop().animate({'marginLeft':moveIndex*options.range})
		})
		}
		//enActive()
		// 标签父容器 宽度
		getliWidth()
		function getliWidth(){
			var contLiWidth = 0;
			contLi.each(function(){
				contLiWidth += $(this).outerWidth(true)
			})
			//contScr.css('width',contLiWidth + 2)
			console.log(contLiWidth + 2)
		}
		
		
		function removeid(idstr){
			for(var i = 0;i++;i<chatList.length){
				if(sender==chatList[i]){
					chatList.splice(i,1)
					return true;
				}
			}
			return false;
			
		}
		
		
		function isInChatList(sender){
			
			for(var i = 0;i<chatList.length;i++){
				
				if(sender==chatList[i]){
					
					return i;
				}
			}
			return -1;
		}
		
		
		//////////////////////////////////////////////////////////
		
		
		
		
		
		sendButton.on('click',function(){
			var chatRoomName = chatList[el.find('.cont-scroll .active') .index()];
			//alert(chatRoomName)
			
			
			if(chatRoomName==undefined){
				alert("Not in a chat room!");
				return;
			}
			//chatRoomName = chatRoomName.substring(5)
			//alert(chatRoomName)
			var msg = '{"type":"'+'msg'+'","chatRoomName":"'+chatRoomName+'","content":"' + $('#chatInput').val() + '", "sender":"'
			+ getCookie("user_name") + '", "receiver":""}';
			chatSocket.send(msg);
		})
		
		
		function putMessageIn(chatRoomName,sender,content,time){
			var date = timestampToTime(time)
				var title
			//= timestampToTime(time)
				if(chatRoomName.length>8)
					title=chatRoomName.substring(0,5)+'...';
					else
						title=chatRoomName;
			sect = "<li class = ''>"+sender+"   "+date+"</li><li class = ''>"+content+"</li>"
			
			
			//alert(isInChatList(chatRoomName))
			if(isInChatList(chatRoomName)==-1){
				chatList.push(chatRoomName)
				
			strLi = "<li ><span>"+title+"</span><a href='javascript:void(0)' class='del'>X</a></li>" 
			strSec = "<div  class='cont-index'>"+sect+"</div>"
			contScr.append(strLi)
			wrapCont.append(strSec)
			resfEl()
			enActive()
			
			contLi.eq(contLi.length-1).addClass('active').siblings(this).removeClass('active')
			contIndex.eq(contLi.length - 1).show().siblings(this).hide()
			getliWidth()
			}
			else{
				
				contIndex.eq(isInChatList(chatRoomName)).append(sect);
			}
			//inpTitle.val(inpSect.val(''))
			
			
		}
		
		
		function getBusinessTables(){
			var pathName = document.location.pathname;
		    var index = pathName.substr(1).indexOf("/");
		    var result = pathName.substr(0,index+1);
		    //alert(uid);
		    
		 
		    //alert(123);
			var theUrl="",theData = "";
			if(uid!="")
				{
				theUrl = result + "/getChatRooms.do";
				theData = "user_id="+ uid;
				}
			else{
				return;
			}
			
			 $.ajax({
					type: "GET",
					url: theUrl,
					contentType: "application/json",
					data: theData,
					success: function(data){
						
						var names='';
						if(data.length!=0){
							//alert(3);
							putMessageIn(data[0],"","","");
							names+=data[0];
						
						for(var i= 1;i<data.length;i++ ){
							putMessageIn(data[i],"","","");
							names+='~';
							names+=data[i];
						}
						}
						
						onChating(names);
					}
				});
		}
		
		
		function onChating(names){
			
			//alert(names);
			
			if ('WebSocket' in window) {
				chatSocket = new WebSocket("ws://" + window.location.host + "/Resturant_Review_Website/ws.do?businessids="+names);
			} else if ('MozWebSocket' in window) {
				chatSocket = new WebSocket("ws://" + window.location.host + "/Resturant_Review_Website/ws.do?businessids="+names);
			} else {
				//如果是低版本的浏览器，则用SockJS这个对象，对应了后台“ws/sockjs”这个注册器，
				//它就是用来兼容低版本浏览器的
				chatSocket = new SockJS("http://127.0.0.1:8080/Resturant_Review_Website/ws/sockjs.do?businessids="+names);
			}
			chatSocket.onopen = function (){
			
			}
			chatSocket.onmessage = function(evt){
				var msgObj = JSON.parse(evt.data);
				//alert('msgObj.chatRoomName  '+msgObj.chatRoomName)
				putMessageIn(msgObj.chatRoomName,msgObj.sender,msgObj.content,msgObj.time)
			}
		}
		
		getBusinessTables();
		
	
		$('#shareTableButton').on('click',function(){
			//alert(this.value);
			if(this.value == "Share Table"){
				this.value="Cancel Shared";
				shareTable();
			}else if(this.value == "Cancel Shared"){
				//alert("haha");
				this.value="Share Table";
				cancelShared();
			}
		})
		
			function timestampToTime(timestamp) {
			if(timestamp=='')
				return '';
		    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
		    Y = date.getFullYear() + '-';
		    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
		    D = date.getDate() + ' ';
		    h = date.getHours() ;
		    if(h<10)
		    	h='0'+h;
		    m = date.getMinutes() ;
		    if(m<10)
		    	m='0'+m;
		    s = date.getSeconds();
		    if(s<10)
		    	s='0'+s;
		    return Y+M+D+h+':'+m+':'+s;
		};
		
		function shareTable(){
			if(uid!=""){
				var pathName = document.location.pathname;
				var index = pathName.substr(1).indexOf("/");
				var result = pathName.substr(0,index+1);
				var bname = data.name.replace(/\+/g, " ");
				var map = {business_name: bname, user_id: uid};
				$.ajax({
					type: "POST",
					url: result + "/saveShareTableUser.do",
					contentType: "application/json",
					data: JSON.stringify(map),
					dataType: "json",
					success: function(data1){
						if(data1.shareTableMsg == 'succeed'){
							alert("You have successfully shared the table!")
							var newName = data.name.replace(/\+/g, " ");
							//alert(newName);
							
							putMessageIn(newName,"","","");
							if(chatSocket==undefined){
								
								onChating(newName);
							}
							else{
								var msg = '{"type":"sharetable","chatRoomName":"'+newName+'","content":"", "sender":"", "receiver":""}';
								chatSocket.send(msg);
							}
								
							
						}else{
							alert("Failed to share table!");
						}
					},
					error:function(data1){  
						//alert(data.regMsg);
					}
				});
			}else{
				alert("Please login first!")
			}
		}
		
		function cancelShared(){
			var pathName = document.location.pathname;
			var index = pathName.substr(1).indexOf("/");
			var result = pathName.substr(0,index+1);
			var bname = data.name.replace(/\+/g, " ");
			var map = {business_name: bname, user_id: uid};
			$.ajax({
				type: "POST",
				url: result + "/deleteShareTableUser.do",
				contentType: "application/json",
				data: JSON.stringify(map),
				dataType: "json",
				success: function(data1){
					if(data1.cancelSharedMsg == 'succeed'){
						alert("You have already canceled the share!");
						var newName = data.name.replace(/\+/g, " ");
						//alert(isInChatList(newName));
						contDel.eq(isInChatList(newName)).trigger("click"); 
						var msg = '{"type":"cancelshare","chatRoomName":"'+newName+'","content":"", "sender":"", "receiver":""}';
						chatSocket.send(msg);
						
					}else{
						alert("Failed to cancel!");
					}
				},
				error:function(data1){  
					//alert(data.regMsg);
				}
			});
		}
		
	}	
})(jQuery);



