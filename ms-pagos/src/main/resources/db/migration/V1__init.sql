CREATE TABLE pago (
    id INT AUTO_INCREMENT PRIMARY KEY,
    reserva_id INT NOT NULL,
    codigo_pago VARCHAR(100) NOT NULL,
    monto DOUBLE NOT NULL,
    metodo_pago VARCHAR(100) NOT NULL,
    pagado BOOLEAN NOT NULL,
    fecha_pago DATE NOT NULL,
    numero_cuotas INT NOT NULL
);

INSERT INTO pago
(reserva_id, codigo_pago, monto, metodo_pago, pagado, fecha_pago, numero_cuotas)
VALUES
    (1, 'PAG-001', 105000, 'Tarjeta credito', true, '2026-05-20', 3),
    (2, 'PAG-002', 78000, 'Debito', true, '2026-05-22', 1),
    (3, 'PAG-003', 40000, 'Transferencia', false, '2026-05-25', 2);