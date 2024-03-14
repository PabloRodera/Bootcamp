import datetime

def calc_total(ticket):
    total = 0
    for elemento in ticket:
        partes = elemento.split(" - ")
        unidades = int(partes[0])
        precio = float(partes[2].replace(",","."))
        if unidades > 0:
            total += precio
        else:
            total -= precio

    print(f"""Total a pagar: {round(total*1.16, 2)}€
Fecha de compra: {datetime.date.today().strftime("%Y-%m-%d")}""")
    

ticket = ["1 - filete de ternera - 30,23","4 - coca cola - 4,20","-2 - coca cola - 1,40","1 - pan - 0,90"]
calc_total(ticket)
