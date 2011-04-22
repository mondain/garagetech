<c:if test="${not empty message}">
    <div id="message" class="${message.type}">${message.text}</div>
    <script type="text/javascript">
        $(function() {
            setTimeout(function() {
                $("#message").hide();
            }, 15000);
        });
    </script>
</c:if>