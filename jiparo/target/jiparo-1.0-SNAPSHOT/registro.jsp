
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Administración de Usuarios</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <script src="//unpkg.com/alpinejs" defer></script>
</head>

<body class="bg-gray-100 min-h-screen flex">

  
  <aside class="w-72 bg-slate-800 text-white p-6 flex flex-col">
    <h2 class="text-2xl font-bold mb-10">Administración</h2>

    <nav class="space-y-3">
      <a href="#" class="block py-2 px-4 rounded-lg hover:bg-slate-500">Usuarios</a>
      <a href="contabilidad.html" class="block py-2 px-4 rounded-lg hover:bg-slate-500">Contabilidad</a>
      <a href="#" class="block py-2 px-4 rounded-lg hover:bg-slate-500">Nomina</a>
      <a href="#" class="block py-2 px-4 rounded-lg hover:bg-slate-500">Inventario</a>
    </nav>

    <div class="mt-auto pt-10">
      <button class="w-full bg-red-600 py-2 rounded-lg hover:bg-red-700">
        Cerrar sesión
      </button>
    </div>
  </aside>


  <main class="flex-1 p-10" x-data="{ openModal: false }">

   
    <div class="flex justify-between items-center mb-8">
      <h1 class="text-3xl font-bold text-gray-800">
        Gestión de Usuarios
      </h1>

      <button 
        @click="openModal = true"
        class="bg-indigo-600 text-white px-5 py-2 rounded-lg shadow hover:bg-indigo-700"
      >
        + Nuevo Empleado
      </button>
    </div>

    
    <div class="bg-white rounded-2xl shadow p-6">
      <div class="overflow-x-auto">
        <table class="w-full text-left">
          <thead>
            <tr class="border-b text-gray-600">
              <th class="py-3">Nombre</th>
              <th>Email</th>
              <th>Departamento</th>
              <th>Puesto</th>
              <th>Estatus</th>
            </tr>
          </thead>
          <tbody class="text-gray-700">
            <tr class="border-b hover:bg-gray-50">
              <td class="py-3">Juan Pérez</td>
              <td>juan@email.com</td>
              <td>Contabilidad</td>
              <td>Analista</td>
              <td class="text-green-600 font-medium">Activo</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    
    <div 
      x-show="openModal"
      class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center"
      x-transition
    >
      <div class="bg-white w-full max-w-3xl rounded-2xl shadow-xl p-8 relative overflow-y-auto max-h-[90vh]">

        
        <button 
          @click="openModal = false"
          class="absolute top-4 right-4 text-gray-500 hover:text-red-600 text-xl"
        >
          ✕
        </button>

        <h2 class="text-2xl font-semibold mb-6">Agregar Nuevo Usuario</h2>

        <form class="grid grid-cols-2 gap-6" action="EmpleadoServlet" method="POST">

          <input type="text" placeholder="Nombre"
            class="border rounded-lg px-3 py-2" name="nombre">

          <input type="text" placeholder="Apellido Paterno"
            class="border rounded-lg px-3 py-2" name="apellido_paterno">

          <input type="text" placeholder="Apellido Materno"
            class="border rounded-lg px-3 py-2" name="apellido_materno">

          <input type="email" placeholder="Email"
            class="border rounded-lg px-3 py-2" name="email">

          <input type="text" placeholder="Teléfono"
            class="border rounded-lg px-3 py-2">

          <input type="text" placeholder="Puesto"
            class="border rounded-lg px-3 py-2">

          <input type="text" placeholder="Departamento"
            class="border rounded-lg px-3 py-2">

          <input type="date" placeholder="Fecha Ingreso"
            class="border rounded-lg px-3 py-2">

          <input type="text" placeholder="Tipo de Contrato"
            class="border rounded-lg px-3 py-2">

          <input type="number" placeholder="Salario Base"
            class="border rounded-lg px-3 py-2">

          <input type="text" placeholder="RFC"
            class="border rounded-lg px-3 py-2">

          <input type="text" placeholder="NSS"
            class="border rounded-lg px-3 py-2">

          <input type="text" placeholder="Cuenta Bancaria"
            class="border rounded-lg px-3 py-2">

          <input type="text" placeholder="Banco"
            class="border rounded-lg px-3 py-2">

          <select class="border rounded-lg px-3 py-2">
            <option value="">Activo</option>
            <option value="1">Sí</option>
            <option value="0">No</option>
          </select>

          <input type="datetime-local" placeholder="Fecha Creación"
            class="border rounded-lg px-3 py-2">

          <input type="datetime-local" placeholder="Fecha Modificación"
            class="border rounded-lg px-3 py-2">

          <div class="col-span-2 flex justify-end pt-4">
            <button type="submit" class="bg-indigo-600 text-white px-6 py-2 rounded-lg hover:bg-indigo-700">
              Guardar Usuario
            </button>
          </div>

        </form>

      </div>
    </div>

  </main>

</body>
</html>
