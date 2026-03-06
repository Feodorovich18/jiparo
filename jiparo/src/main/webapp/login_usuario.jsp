
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>JS_Contabilidad</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="min-h-screen bg-slate-800 flex items-center justify-center px-6">

  <!-- CARD LOGIN -->


  <div class="bg-white rounded-2xl shadow-2xl w-full max-w-md p-10">

    <!-- Encabezado -->
    <div class="text-center mb-8">
      <h2 class="text-3xl font-bold text-gray-800 mb-2">
        Área de Clientes
      </h2>
      <p class="text-gray-500 text-sm">
        Acceso seguro a tus servicios contables
      </p>
    </div>

    <!-- Formulario -->
    <form id="loginForm" novalidate class="space-y-6" action="LoginServlet" method="POST">

      <!-- Email -->
      <div>
        <label class="block text-gray-700 font-medium mb-2">
          Usuario
        </label>
        <input type="user" id="user"
          class="w-full px-4 py-3 rounded-lg border border-gray-300 focus:ring-2 focus:ring-blue-600 focus:outline-none transition"
          placeholder="Ingresa tu usuario" name="username" required>
        <p class="text-red-600 text-sm mt-2 hidden" id="erroruser">
          Ingresa un correo válido.
        </p>
      </div>

      <!-- Password -->
      <div>
        <label class="block text-gray-700 font-medium mb-2">
          Contraseña
        </label>
        <input type="password" id="password"
          class="w-full px-4 py-3 rounded-lg border border-gray-300 focus:ring-2 focus:ring-blue-600 focus:outline-none transition"
          placeholder="••••••••" name="password" required>
        <p class="text-red-600 text-sm mt-2 hidden" id="errorPassword">
          La contraseña es obligatoria.
        </p>
      </div>

      <!-- Recordar sesión -->
      <div class="flex items-center justify-between text-sm">
        <label class="flex items-center space-x-2">
          <input type="checkbox" class="rounded text-blue-600 focus:ring-blue-600">
          <span class="text-gray-600">Recordar sesión</span>
        </label>

        <a href="#" class="text-blue-700 hover:underline">
          ¿Olvidaste tu contraseña?
        </a>
      </div>

      <!-- Botón -->
      <div class="pt-4">
        <button type="submit"
          class="w-full bg-slate-800 text-white py-3 rounded-lg font-semibold shadow hover:bg-blue-800 transition duration-600">
          Iniciar sesión
        </button>

        <!-- Seguridad -->
        <p class="text-xs text-gray-500 text-center mt-4">
          🔒 Conexión protegida y cifrada.
        </p>
      </div>

    </form>
    
        <%
            String error = request.getParameter("error");
            if (error != null && error.equals("1")) {
        %>
            <div class="error-message show">
                Usuario o contraseña incorrectos
            </div>
        <%
            } else if (error != null && error.equals("2")) {
        %>
            <div class="error-message show">
                Error en la base de datos. Intente más tarde.
            </div>
        <%
            }
        %>

  </div>

<!--
<script>
const loginForm = document.getElementById('loginForm');

loginForm.addEventListener('submit', function(e){
  e.preventDefault();

  let valid = true;

  const email = document.getElementById('loginEmail');
  const password = document.getElementById('loginPassword');
  const errorEmail = document.getElementById('errorLoginEmail');
  const errorPassword = document.getElementById('errorLoginPassword');

  // Reset
  [email, password].forEach(el => el.classList.remove('border-red-500'));
  [errorEmail, errorPassword].forEach(el => el.classList.add('hidden'));

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  if (!emailRegex.test(email.value.trim())) {
    errorEmail.classList.remove('hidden');
    email.classList.add('border-red-500');
    valid = false;
  }

  if (password.value.trim().length < 1) {
    errorPassword.classList.remove('hidden');
    password.classList.add('border-red-500');
    valid = false;
  }

  if (valid) {
    alert("Login validado (pendiente conexión backend)");
    loginForm.reset();
  }
});
</script>
-->

</body>
</html>
       