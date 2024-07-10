  /*<![CDATA[*/
        var stompClient = null;
        var socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/mesaje', function(mesaj) {
                showMessage(JSON.parse(mesaj.body).content);
            });
        });

        function sendMessage() {
            var messageContent = document.getElementById('message').value.trim();
            if(messageContent && stompClient) {
                var chatMessage = {
                    content: messageContent,
                    // Adaugă alte atribute necesare
                };
                stompClient.send("/app/mesaj", {}, JSON.stringify(chatMessage));
                document.getElementById('message').value = '';
            }
        }

        function showMessage(message) {
            var messageList = document.getElementById('messageList');
            var messageElement = document.createElement('li');
            messageElement.innerText = message;
            messageList.appendChild(messageElement);
        }

        document.getElementById('messageForm').addEventListener('submit', function(event) {
            event.preventDefault();
            sendMessage();
        });
        /*]]>*/