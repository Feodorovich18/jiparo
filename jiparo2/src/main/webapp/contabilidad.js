// ESPERA: El formulario tiene ID "miFormulario"
    const formulario = document.getElementById('form_alta_empresa');

    //const resultado = document.getElementById('resultado');
    //const respuestaJSON = document.getElementById('respuestaJSON');

    const btnEnviar = document.getElementById('btnEnviar');

    // 1. Escuchar el evento 'submit' del formulario
    formulario.addEventListener('submit', async function(event) {

        event.preventDefault();

        // 2. Mostrar indicador de carga
        btnEnviar.disabled = true;
        btnEnviar.textContent = 'Enviando...';
        btnEnviar.classList.add('opacity-50');

        // 3. Obtener los valores del formulario
        const nombre_empresa = document.getElementById('nombre_empresa').value;
        const rfc = document.getElementById('rfc').value;


        // 4. Crear objeto JavaScript con los datos
        const datosEmpresa = {
            nombre_empresa: nombre_empresa,
            rfc: rfc,
        };

        console.log('📤 Enviando al servidor:', datosEmpresa);

        try {
            // 5. FETCH
            const response = await fetch('/jiparo2/api/empresaServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json', // Enviamos JSON
                },
                body: JSON.stringify(datosEmpresa)  // Convertir objeto JS a texto JSON
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
    });