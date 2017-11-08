# PruebaImaginamos


Falta implementar:

1. Clases Dao, model y service de una entidad llamada compras.
Esta entidad nueva incluiria una fecha, una lista de productos y una tinda (se presume que cada compra es solo en una tienda).
2. El menajeo de la base de datos en la clase maestra.
Esta clase se encarga de hacer el acceso a la base de datos y tras acciones necesarias generaes (hacer commit, y set el autocommit a false para majear adecauadamente la normalización).


La base de datos constaria de 5 tablas, Usuario, prductos y tienda con todo lo necesario para sus entidades ( descrito en el enunciado), 
una relacionando el producto y la tienda en donde se relaciones los PK de los productos que estan el las tiendas como FK (siendo el id unico el PK de ambos). Y una tabla de compras en cuyas columnas incluyen: fecha, , identificador de la tienda e identificador del producto. No hay un identificador unico pues la compra puede incluir varios productos. 
