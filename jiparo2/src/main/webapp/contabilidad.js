const formulario = document.getElementById('form_create_empresa');

const btnEnviar = document.getElementById('btn_create_empresa');

    // Escuchar el evento 'submit' del formulario
    formulario.addEventListener('submit', async function(event) {

        event.preventDefault();


        const numero_empresa = document.getElementById('create_numero_empresa').value;
        const nombre_empresa = document.getElementById('create_nombre_empresa').value;
        const rfc = document.getElementById('create_rfc').value;
        const direccion_fiscal = document.getElementById('create_direccion_fiscal').value;
        const descripcion = document.getElementById('create_descripcion').value;



        // Crear objeto JavaScript con los datos
        const datosEmpresa = {
            numero_empresa : numero_empresa,
            nombre_empresa: nombre_empresa,
            rfc: rfc,
            direccion_fiscal: direccion_fiscal,
            descripcion: descripcion,
        };

        console.log('Enviando al servidor:', datosEmpresa);

        try {
            // FETCH
            const response = await fetch('/jiparo2/api/contabilidadServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json', // Enviamos JSON
                },
                body: JSON.stringify(datosEmpresa)  // Convertir objeto JS a texto JSON
            });

            // Verificar si la respuesta es correcta
            if (!response.ok) {
                throw new Error(`Error HTTP: ${response.status}`);
            }

            // Parsear la respuesta JSON que viene del servlet
            const respuestaServidor = await response.json();

            console.log('Respuesta del servidor:', respuestaServidor);

            if(respuestaServidor.exito){
                    window.location.href = '/jiparo2/contabilidad.html';
            } else {
                alert('Error al crear empresa: ' + respuestaServidor.mensaje);
            }

            // limpiar el formulario
            formulario.reset();

        } catch (error) {
            console.error('Error:', error);
            alert('Error al enviar los datos: ' + error.message);
        }
    });

    //***************************************************************************


/********************************************************************
*  MOSTRAR EMPRESAS
* @ res = objeto de Javascript
*******************************************************************/

const btn_mostrar_empresas = document.getElementById("btn_mostrar_empresas");
btn_mostrar_empresas.addEventListener('click', () => {

    fetch("/jiparo2/api/contabilidadServlet", {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
    })
        .then((res) => (res.ok ? Promise.resolve(res) : Promise.reject(res)))
        .then((res) => res.json())
        .then((res) => {
            console.log(res); // FLAG, yooooo

            const filas = res.map(empresa => `
        <tr class="hover:bg-white/5 transition-colors duration-200">
            <td class="px-6 py-4 whitespace-nowrap">
                <div class="text-sm text-slate-400">${empresa.numero_empresa}</div>
            </td>
            <td class="px-6 py-4 whitespace-nowrap">
                <div class="flex items-center">
                    <div class="flex-shrink-0 h-10 w-10 bg-gradient-to-br from-blue-500 to-blue-700 rounded-xl flex items-center justify-center shadow-lg">
                        <i class="fa-solid fa-building text-white text-sm"></i>
                    </div>
                 
                    <a href="#" onclick="irAEmpresa(${empresa.id_empresa})" class="ml-4">
                    
                        <div class="text-sm font-semibold text-white hover:text-blue-300 transition">${empresa.nombre_empresa}</div>
                        <div class="text-xs text-slate-400">demo@empresa.com</div>
                    </a>
                </div>
            </td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-slate-300">${empresa.rfc}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-slate-300">${empresa.direccion_fiscal}</td>
            <td class="px-6 py-4 whitespace-nowrap">
                <span class="px-2.5 py-1 inline-flex text-xs font-semibold rounded-full bg-emerald-500/20 text-emerald-400 border border-emerald-500/30">Activo</span>
            </td>
            <td class="px-6 py-4 whitespace-nowrap text-sm">
                <div class="flex gap-2">
                    <button class="p-1.5 text-amber-400 hover:text-amber-300 hover:bg-amber-400/10 rounded-lg transition">
                        <i class="fa-solid fa-pen"></i>
                    </button>
                    <button class="p-1.5 text-red-400 hover:text-red-300 hover:bg-red-400/10 rounded-lg transition">
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
function irAEmpresa(idEmpresa) {
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