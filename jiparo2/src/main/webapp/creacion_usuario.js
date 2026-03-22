const formulario = document.getElementById('form_crear_usuario');

const btnEnviar = document.getElementById('btnEnviar');

formulario.addEvenListener('submit', async function(event){
    event.preventDefault();

            //Indicador de carga
            btnEnviar.disabled = true;
            btnEnviar.textContent = 'Verificando...';
            btnEnviar.classList.add('opacity-50');

    const nombre = document.getElementById('nombre').value;
    const apellido_paterno = document.getElementById('apellido_paterno').value;
    const apellido_materno = document.getElementById('apellido_materno').value;
    const email = document.getElementById('email').value;
    const telefono = document.getElementById('telefono').value;

    const activacion_contabilidad = document.getElementById('activacion_contabilidad').checked;
    const activacion_nomina = document.getElementById('activacion_nomina').checked;
    const activacion_inventario = document.getElementById('activacion_inventario').checked;
    const activacion_facturacion = document.getElementById('activacion_facturacion').checked;
    const activacion_reportes = document.getElementById('activacion_reportes').checked;
    const activacion_administracion = document.getElementById('activacion_administracion').checked;

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
        activacion_administracion: activacion_administracion
        rolValue: rolValue;
    };

    console.log('Enviando al servidor:' datosUsuario);

    try {
                // FETCH
                const response = await fetch('/jiparo2/api/usuarioServlet', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json', // Enviamos JSON
                    },
                    body: JSON.stringify(datosUsuario)  // Convertir objeto JS a texto JSON
                });

                // 6. Verificar si la respuesta es correcta
                if (!response.ok) {
                    throw new Error(`Error HTTP: ${response.status}`);
                }

                // 7. Parsear la respuesta JSON que viene del servlet
                const respuestaServidor = await response.json();

                console.log('📥 Respuesta del servidor:', respuestaServidor);

                // 8. Mostrar la respuesta en el HTML
                // respuestaJSON.textContent = JSON.stringify(respuestaServidor, null, 2);
                // resultado.classList.remove('hidden');

                // limpiar el formulario
                formulario.reset();

            } catch (error) {
                console.error('Error:', error);
                alert('Error al enviar los datos: ' + error.message);
            } finally {
                // 10. Restaurar el botón
                btnEnviar.disabled = false;
                btnEnviar.textContent = 'Enviar datos al Servlet';
                btnEnviar.classList.remove('opacity-50');
            }

})












// Para checkbox individual
const formData = {
    aceptaTerminos: document.getElementById('aceptaTerminos').checked,
    recibirNoticias: document.getElementById('recibirNoticias').checked,

    // Para grupo de checkboxes con el mismo nombre
    opciones: Array.from(document.querySelectorAll('input[name="opcionMultiple"]:checked'))
                .map(cb => cb.value)
};

// Convertir a JSON
const jsonData = JSON.stringify(formData);