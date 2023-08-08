document.addEventListener('DOMContentLoaded', function() {
const chatMessages = document.getElementById('chatMessages');
const messageInput = document.getElementById('messageInput');
const sendButton = document.getElementById('sendButton');

sendButton.addEventListener('click', function() {
const message = messageInput.value;
if (message.trim() !== '') {
const messageDiv = document.createElement('div');
messageDiv.className = 'message';
messageDiv.textContent = 'You: ' + message;
chatMessages.appendChild(messageDiv);
messageInput.value = '';
// You might want to send the message to the server here
}
});
});