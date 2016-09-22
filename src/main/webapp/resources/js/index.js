var working = false;
$('.login').on('submit', function(e) {
  e.preventDefault();
  if (working) return;
  working = true;
  var $this = $(this),
  $state = $this.find('button > .state');
  $this.addClass('loading');
  $state.html('Authenticating');
  document.forms['loginForm'].submit();  
});


$(document).ready(function() {
	$this=$('#loginForm');
	$exclaim=$('#exclaim');
	$this.addClass('loading');
	$state = $this.find('button > .state');
if(msg!==''){
	$this.addClass('ok');
	$state.html(msg);
	//show msg for 2.5s
	setTimeout(function() {
		$state.html('Log In');
		$this.removeClass('ok loading');
		$exclaim.removeClass('fa fa-exclamation-circle');						
	}, 2500);
}
else if(errorMsg!==''){
	$this.addClass('notok');
	$state.html(errorMsg);
	$exclaim.addClass('fa fa-exclamation-circle');
	$exclaim.removeClass('spinner');			
		
	
	//show msg for 3s
	setTimeout(function() {
		$state.html('Log In');
		$this.removeClass('notok loading');	
		$exclaim.removeClass('fa fa-exclamation-circle');
		$exclaim.addClass('spinner');
				
	}, 3000);
}
else{
	setTimeout(function() {
		$state.html('Log In');
		$this.removeClass('ok loading');	
		}, 1000);
}
	
$this.removeClass('hidden');

});

