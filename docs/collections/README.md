# Collection

Nesta pasta está as coleções do Postman separado conforme as classes de controllers

<table>
   <tr>
      <td style="text-align: center" colspan="3">Categoria</td>
   </tr>
   <tr>
      <td>/categorias</td>
      <td>GET</td>
      <td>Retornar todas as categorias no banco de dados</td>
   </tr>
   <tr>
      <td>/categorias/{ID}</td>
      <td>GET</td>
      <td>Retornar a categoria do ID informado junto com os produtos dela</td>
   </tr>
   <tr>
      <td>/categorias</td>
      <td>POST</td>
      <td>Cadastra nova categoria</td>
   </tr>
</table>

<table>
   <tr>
      <td style="text-align: center" colspan="3">Produto</td>
   </tr>
   <tr>
      <td>/produtos/{ID}</td>
      <td>GET</td>
      <td>Retornar o produto do ID informado junto com a categoria</td>
   </tr>
</table>