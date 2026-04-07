/*******************************************
 * AGREGAR USUARIO
 ******************************************/

const formulario = document.getElementById('form_create_usuario');

const btnSubmit = document.getElementById('btn_create_usuario');

formulario.addEventListener('submit', async function(event){
    event.preventDefault();

    //Indicador de carga
    btnSubmit.disabled = true;
    btnSubmit.textContent = 'Verificando...';
    btnSubmit.classList.add('opacity-50');

    const nombre = document.getElementById('create_nombre_usuario').value;
    const apellido_paterno = document.getElementById('create_apellido_paterno_usuario').value;
    const apellido_materno = document.getElementById('create_apellido_materno_usuario').value;
    const email = document.getElementById('create_email_usuario').value;
    const telefono = document.getElementById('create_telefono_usuario').value;

    const activacion_contabilidad = document.getElementById('create_activacion_contabilidad').checked;
    const activacion_nomina = document.getElementById('create_activacion_nomina').checked;
    const activacion_inventario = document.getElementById('create_activacion_inventario').checked;
    const activacion_facturacion = document.getElementById('create_activacion_facturacion').checked;
    const activacion_reportes = document.getElementById('create_activacion_reportes').checked;
    const activacion_administracion = document.getElementById('create_activacion_administracion').checked;

    const username = document.getElementById('create_username').value;
    const password = document.getElementById('create_password').value;
    const rol = document.getElementById('rol');
    const rolValue = rol.value;


    const datosUsuario = {
        nombre: nombre,
        apellido_paterno: apellido_paterno,
        apellido_materno: apellido_materno,
        email: email,
        telefono: telefono,

        activacion_contabilidad: activacion_contabilidad,
        activacion_nomina: activacion_nomina,
        activacion_inventario: activacion_inventario,
        activacion_facturacion: activacion_facturacion,
        activacion_reportes: activacion_reportes,
        activacion_administracion: activacion_administracion,
        username: username,
        password: password,
        rol: rolValue,
    };

    console.log('Enviando al servidor:' + datosUsuario);

    try {
        // FETCH
        const response = await fetch('/jiparo2/api/usuarioServlet?action=agregar_usuario', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json', // Enviamos JSON
            },
            body: JSON.stringify(datosUsuario)  // Convertir objeto JS a texto JSON
        });

        // Verificar si la respuesta es correcta
        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        // Parsear la respuesta JSON que viene del servlet
        const respuestaServidor = await response.json();

        console.log('Respuesta del servidor:', respuestaServidor);


        if(respuestaServidor.exito){
            window.location.href = '/jiparo2/dashboard_admin.html';
        } else {
            alert('Error al crear usuario: ' + respuestaServidor.mensaje);
        }

        // limpiar el formulario
        formulario.reset();

    } catch (error) {
        console.error('Error:', error);
        alert('Error al enviar los datos: ' + error.message);
    }

});




/*******************************************
 * OBTENER LISTA USUARIOS
 ******************************************/

const btn_mostrar_usuarios = document.getElementById("btn_mostrar_usuarios");
btn_mostrar_usuarios.addEventListener('click', () => {

    fetch("/jiparo2/api/usuarioServlet?action=", {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
    })
        .then((res) => (res.ok ? Promise.resolve(res) : Promise.reject(res)))
        .then((res) => res.json())
        .then((res) => {
            console.log(res); // FLAG, yooooo

            const filas = res.map(usuario => `
        <tr class="hover:bg-white/5 transition-colors duration-200">
                    <td class="py-4 px-6">
                        <div class="flex items-center gap-3">
                            <div
                                    class="w-10 h-10 bg-gradient-to-br from-blue-500 to-blue-700 rounded-xl flex items-center justify-center shadow-lg">
                                <i class="fa-solid fa-user text-white text-sm"></i>
                            </div>
                            <div>
                                <p class="font-semibold text-white">${usuario.nombre_completo}</p>
                                <p class="text-xs text-slate-400">ID: USR-001</p>
                            </div>
                        </div>
                    </td>
                    <td class="py-4 px-6 text-slate-300">${usuario.email}</td>
                    <td class="py-4 px-6">
                <span class="px-3 py-1 bg-blue-500/20 text-blue-400 text-xs font-semibold rounded-full border border-blue-500/30">
                    ${usuario.cantidad_licencias} licencias
                </span>
                    </td>
                    <td class="py-4 px-6">
                <span
                        class="px-3 py-1 bg-emerald-500/20 text-emerald-400 text-xs font-semibold rounded-full border border-emerald-500/30">${usuario.rol}</span>
                    </td>
                    <td class="py-4 px-6">
                <span
                        class="px-3 py-1 bg-emerald-500/20 text-emerald-400 text-xs font-semibold rounded-full border border-emerald-500/30">Activo</span>
                    </td>
                    <td class="py-4 px-6 text-right">
                        <div class="flex gap-2 justify-end">
                            <button @click="openEdit = true"
                                    class="p-2 text-amber-400 hover:text-amber-300 hover:bg-amber-400/10 rounded-lg transition">
                                <i class="fa-solid fa-pen"></i>
                            </button>
                            <button @click="openPrograms = true"
                                    class="p-2 text-blue-400 hover:text-blue-300 hover:bg-blue-400/10 rounded-lg transition">
                                <i class="fa-solid fa-cube"></i>
                            </button>
                            <button class="p-2 text-red-400 hover:text-red-300 hover:bg-red-400/10 rounded-lg transition">
                                <i class="fa-solid fa-trash"></i>
                            </button>
                        </div>
                    </td>
        </tr>
    `).join("");  // une todas las filas en un solo string

            document.getElementById("insert_empresa").innerHTML = filas;
        });
});


//========================== Metodo para pasar el id_empresa al servlet==============
function irAUsuario(idUsuario) {
    fetch("/jiparo2/api/empresaServlet", {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id_empresa: idEmpresa })
    })
        .then(res => res.json())
        .then(res => {
            if (res.status === "ok") {
                window.location.href = "empresa.html";
            }
        });
}