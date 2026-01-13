/*1.Top clientes con más facturas*/
select  c.id as Id_Cliente, 
		c.nombre as Nombre,
		count(f.id) as Cantidad_Facturas
from cliente c
join factura f on f.cliente_id = c.id
group by c.id, c.nombre
order by Cantidad_Facturas desc;

/*Top clientes que más gastaron*/
select c.id as id_cliente,
	   c.nombre as Nombre,
	   sum(fd.cantidad*p.precio) as Monto_Gastado
from cliente c 
join factura f on f.cliente_id = c.id 
join factura_detalle fd on fd.factura_id = f.id
join producto p on p.id = fd.producto_id
group by c.id, c.nombre
order by Monto_Gastado desc;

/*Top monedas más utilizadas*/
select m.nombre as Moneda, 
	   count(f.moneda_id) as Cantidad
from moneda m 
join factura f on f.moneda_id = m.id
group by m.nombre 
order by Cantidad desc;

/*Top proveedor de productos por ganancia */
select pr.id as id_proveedor,
	   pr.nombre as proveedor,
	   cast(sum(p.precio*f.cantidad) as int) as ganancia
from proveedor pr
join producto p on p.proveedor_id = pr.id
join factura_detalle f on f.producto_id = p.id
group by pr.id order by ganancia desc;

/*Top proveedores por cantidad de productos proveidos */
select pr.id as id_proveedor,
	   pr.nombre as nombre_proveedor,
	   count(p.proveedor_id) as cantidad_proveidos
from producto p 
join proveedor pr on p.proveedor_id = pr.id
group by pr.id, pr.nombre, p.proveedor_id 
order by cantidad_proveidos desc;

/*Top proveedores por cantidad total de productos proveidos*/
select pr.id as id_proveedor,
	   pr.nombre as proveedor,
	   cast(sum(f.cantidad) as int) as ganancia
from proveedor pr
join producto p on p.proveedor_id = pr.id
join factura_detalle f on f.producto_id = p.id
group by pr.id order by "ganancia" desc;

/*Productos mas vendidos*/
select p.id as id_producto, 
       p.nombre as nonbre,
       sum(fd.cantidad) as cantidad_vendidos
 from proveedor p 
 join producto pr on p.id = pr.proveedor_id
 join factura_detalle fd on fd.producto_id = pr.id
 group by p.id, pr.id order by cantidad_vendidos desc;

/*Productos menos vendidos*/	   
select p.id as id_producto, 
       p.nombre as nonbre,
       sum(fd.cantidad) as cantidad_vendidos
 from proveedor p 
 join producto pr on p.id = pr.proveedor_id
 join factura_detalle fd on fd.producto_id = pr.id
 group by p.id, p.nombre order by cantidad_vendidos asc;

 
/*Consulta que muestre fecha de emisión de factura, nombre y apellido del cliente,
 *  nombres de productos de esa factura, cantidades compradas, nombre de tipo de factura de una 
 * factura específica */
select f.fecha_emision as Fecha_Emision,
	   c.nombre as Nombre_Cliente,
	   c.apellido as Apellido_Cliente,
	   p.nombre as Nombre_producto,
	   sum(fd.cantidad) as Cantidades_Compradas,
	   ft.nombre as Tipo_de_factura
from factura f 
join cliente c on c.id = f.cliente_id
join factura_detalle fd on fd.factura_id = f.id
join producto p on p.id = fd.producto_id
join factura_tipo ft on ft.id = f.factura_tipo_id
group by f.id, c.nombre, c.apellido, p.nombre, ft.nombre
		order by Cantidades_Compradas desc;

/*Montos de facturas ordenadas según totales*/
select f.id as Factura,
	   sum(fd.cantidad*p.precio) as Totales
from factura f 
join factura_detalle fd 
	on fd.factura_id = f.id 
join producto p
	on p.id = fd.producto_id
group by f.id order by  Totales desc;

/*Mostrar el iva 10% de los montos totales de facturas (suponer que todos los productos tienen IVA 10%)*/
select f.id as Factura,
	   sum((fd.cantidad*p.precio)/11) as "Iva10%",
	   sum(fd.cantidad*p.precio) as Total_con_Iva
from factura f 
join factura_detalle fd 
	on fd.factura_id = f.id 
join producto p
	on p.id = fd.producto_id
group by f.id order by f.id  desc;