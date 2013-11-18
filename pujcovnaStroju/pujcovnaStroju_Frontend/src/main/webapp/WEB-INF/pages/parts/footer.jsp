<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

    <div class="footer">
		<p>
			<spring:message code="${pageFooter}" text="" />
		</p>
    </div>
</div>

<script type="text/javascript">
    $(function(){
	    $('.datePicker').appendDtpicker();
    });
</script>

</body>
</html>