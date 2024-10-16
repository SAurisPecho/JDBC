package servicios;
import java.sql.SQLException;
import java.util.List;

import entidades.DetallePedido;
import entidades.Pedido;
import persistencia.PedidoDAO;

public class PedidoServicio {
    private PedidoDAO pd;

    public PedidoServicio() {
        this.pd = new PedidoDAO();
    }

    public void guardarPedido(Pedido pedido, List<DetallePedido> detalles) throws Exception {
        try {
            validacionesPedido(pedido);

            for (DetallePedido detalle : detalles) {
                if (detalle.getIdProducto() <= 0) {
                    throw new IllegalArgumentException("El ID del producto debe ser un número positivo");
                }
                if (detalle.getCantidad() <= 0) {
                    throw new IllegalArgumentException("La cantidad debe ser un número positivo");
                }
                if (detalle.getPrecioUnidad() <= 0) {
                    throw new IllegalArgumentException("El precio unitario debe ser un número positivo");
                }
            }

            pd.guardarPedido(pedido, detalles);
            System.out.println("Pedido y detalles guardados correctamente.");
        } catch (SQLException | IllegalArgumentException e) {
            throw new Exception("Error al crear el pedido: " + e.getMessage());
        }
    }
    
    public void validacionesPedido(Pedido pedido) throws Exception {
        if (pedido.getFechaEsperada() == null) {
            throw new IllegalArgumentException("La fecha esperada no puede ser nula.");
        }
        if (pedido.getFechaEntrega() == null) {
            throw new IllegalArgumentException("La fecha de entrega no puede ser nula.");
        }
        if (pedido.getEstado() == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        }
        if (pedido.getIdCliente() <= 0) {
            throw new IllegalArgumentException("El id del cliente no es valido.");
        }
    }

    public void listarPedidosPorCliente (int idCliente) throws Exception{
        if (idCliente <= 0) {
            throw new IllegalArgumentException("El ID del cliente debe ser un número positivo.");
        }
        pd.listarPedidosPorCliente(idCliente);
    }

    public void listarPedidosPorEstado (String estado) throws Exception{
        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        }
        pd.listarPedidosPorEstado(estado);
    }

    public void listarPedidosPorProducto (int idProducto) throws Exception {
        if (idProducto <= 0 ) {
            throw new IllegalArgumentException("El id del producto no es valido.");
        }
        pd.listarPedidosPorProducto(idProducto);
    }
} 

