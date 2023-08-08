window.onload = function() {
    fetch('/data')
        .then(response => response.json())
        .then(data => {
            document.getElementById('users').textContent = data.users;
            document.getElementById('sales').textContent = data.sales.toFixed(2);
            document.getElementById('tasks').textContent = data.tasks;
        });
}
