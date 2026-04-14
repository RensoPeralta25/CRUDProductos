package com.codewithmosh.crud;
import com.codewithmosh.crud.enums.CategoriaProducto;
import com.codewithmosh.crud.enums.UnidadMedida;
import com.codewithmosh.crud.pojo.Producto;
import com.codewithmosh.crud.service.ProductoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {
    private final ProductoService productoService;
    private final TextField nombre = new TextField("Nombre");
    private final NumberField precioUnitario = new NumberField("Precio unitario");
    private final ComboBox<CategoriaProducto> categoria = new ComboBox<>("Categoria");
    private final ComboBox<UnidadMedida> unidadMedida = new ComboBox<>("Unidad de medida");
    private final Button guardar = new Button("Guardar");
    private final Button nuevo = new Button("Nuevo");
    private final Button eliminar = new Button("Eliminar seleccionado");
    private final Button actualizar = new Button("Actualizar seleccionado");
    private final Grid<Producto> grid = new Grid<>(Producto.class, false);
    private Producto productoSeleccionado;

    public MainView(ProductoService productoService) {
        this.productoService = productoService;
        configurarFormulario();
        configurarGrid();
        configurarEventos();
        add(
                nombre,
                precioUnitario,
                categoria,
                unidadMedida,
                new HorizontalLayout(guardar, nuevo, eliminar, actualizar),
                grid
        );
        actualizarGrid();
    }
    private void configurarFormulario() {
        categoria.setItems(CategoriaProducto.values());
        unidadMedida.setItems(UnidadMedida.values());
        nombre.setWidthFull();
        precioUnitario.setWidthFull();
        categoria.setWidthFull();
        unidadMedida.setWidthFull();
        setSizeFull();
    }
    private void configurarGrid() {
        grid.addColumn(Producto::getId_producto).setHeader("ID");
        grid.addColumn(Producto::getNombre).setHeader("Nombre");
        grid.addColumn(Producto::getPrecioUnitario).setHeader("Precio");
        grid.addColumn(Producto::getCategoria).setHeader("Categoria");
        grid.addColumn(Producto::getUnidadMedida).setHeader("Unidad de medida");
        grid.setWidthFull();
        grid.setHeight("350px");
    }
    private void configurarEventos() {
        guardar.addClickListener(event -> guardarProducto());
        nuevo.addClickListener(event -> limpiarFormulario());
        eliminar.addClickListener(event -> eliminarProducto());
        actualizar.addClickListener(event -> actualizarProducto());
        grid.asSingleSelect().addValueChangeListener(event -> {
            productoSeleccionado = event.getValue();
            if (productoSeleccionado != null) {
                cargarFormulario(productoSeleccionado);
            }
        });
    }
    private void guardarProducto() {
        if (!formularioValido()) {
            Notification.show("Completa todos los campos");
            return;
        }
        Producto producto = productoSeleccionado != null
                ? productoSeleccionado
                : new Producto(null, "", 0, null, null);
        producto.setNombre(nombre.getValue());
        producto.setPrecioUnitario(precioUnitario.getValue());
        producto.setCategoria(categoria.getValue());
        producto.setUnidadMedida(unidadMedida.getValue());
        if (producto.getId_producto() == null) {
            productoService.save(producto);
            Notification.show("Producto creado");
        } else {
            productoService.update(producto.getId_producto(), producto);
            Notification.show("Producto actualizado");
        }
        limpiarFormulario();
        actualizarGrid();
    }
    private void eliminarProducto() {
        if (productoSeleccionado == null || productoSeleccionado.getId_producto() == null) {
            Notification.show("Selecciona un producto");
            return;
        }
        productoService.deleteById(productoSeleccionado.getId_producto());
        Notification.show("Producto eliminado");
        limpiarFormulario();
        actualizarGrid();
    }

    private void actualizarProducto() {
        if (productoSeleccionado == null || productoSeleccionado.getId_producto() == null) {
            Notification.show("Selecciona un producto para actualizar");
            return;
        }
        if (!formularioValido()) {
            Notification.show("Completa todos los campos");
            return;
        }
        Producto productoActualizado = new Producto(
                productoSeleccionado.getId_producto(),
                nombre.getValue(),
                precioUnitario.getValue(),
                categoria.getValue(),
                unidadMedida.getValue()
        );
        productoService.update(productoSeleccionado.getId_producto(), productoActualizado);
        Notification.show("Producto actualizado");
        limpiarFormulario();
        actualizarGrid();
    }

    private void cargarFormulario(Producto producto) {
        nombre.setValue(producto.getNombre() != null ? producto.getNombre() : "");
        precioUnitario.setValue(producto.getPrecioUnitario());
        categoria.setValue(producto.getCategoria());
        unidadMedida.setValue(producto.getUnidadMedida());
    }
    private void actualizarGrid() {
        grid.setItems(productoService.findAll());
    }
    private void limpiarFormulario() {
        productoSeleccionado = null;
        nombre.clear();
        precioUnitario.clear();
        categoria.clear();
        unidadMedida.clear();
        grid.deselectAll();
    }
    private boolean formularioValido() {
        return !nombre.isEmpty()
                && precioUnitario.getValue() != null
                && categoria.getValue() != null
                && unidadMedida.getValue() != null;
    }
}