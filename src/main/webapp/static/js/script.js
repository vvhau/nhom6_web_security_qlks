function showResult(str) {
	console.log("hello");
}

function roomSearch() {
    uri = "/api/get_rooms?" +
        "so-nguoi=" + document.querySelector("#so-nguoi").value +
        "&check-in=" + document.querySelector("#check-in").value +
        "&check-out=" + document.querySelector("#check-out").value;
    fetch(uri, {
        method: 'get',
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function(res) {
        console.info(res)
        return res.json()
    }).then(function(data) {
        if (data.length == 0) {
            alert("Không có phòng thỏa điều kiện");
        }
        console.info(data)

        let table = document.getElementById("rooms-list");
        table.innerHTML = '';

        for (let p of data) {
            let r = table.insertRow(table.rows.length);

            let btn = document.createElement('button');
            btn.className = 'btn btn-primary';
            btn.innerText = 'Đặt phòng';
            btn.addEventListener("click", () => {book(p.id_phong, p.ten_phong)});

            r.insertCell(0).innerText = data.indexOf(p) + 1;
            r.insertCell(1).innerText = p.ten_phong;
            r.insertCell(2).innerText = p.loai_phong;
            r.insertCell(3).innerText = p.don_gia;
            r.insertCell(4).innerText = p.trang_thai;
            r.insertCell(5).innerText = p.ghi_chu;
            r.insertCell(6).appendChild(btn);
        }
    })
}


function book(roomId, roomName) {
    if (document.querySelector('#check-in').value == '' ||
        document.querySelector('#check-out').value == '') {
            alert('Vui lòng chọn check in, check out');
            return;
        }
    document.querySelector('#ten-phong-pt').value = roomName;
    document.querySelector('#id-phong-pt').value = roomId;
    document.querySelector('#check-in-pt').value = document.querySelector('#check-in').value;
    document.querySelector('#check-out-pt').value = document.querySelector('#check-out').value;
    document.querySelector('.modal').style.display='flex';
}

function createTableBooking(row) {
    let table = document.getElementById("tb-booking");
    table.innerHTML = '';

    for (let i = 0; i < row; i++) {
        let r = table.insertRow(i);

        r.insertCell(0).innerText = i + 1;
        r.insertCell(1).appendChild(document.createElement('input'));
        r.insertCell(2).appendChild(document.createElement('input'));
        r.insertCell(3).appendChild(document.createElement('input'));
    }
}

function createBooking() {
    let table = document.getElementById("tb-booking");

    const data = [];

    for (let r of table.children) {
        data.push({
            ten_kh: r.children[1].children[0].value,
            cmnd: r.children[2].children[0].value,
            dia_chi: r.children[3].children[0].value
        });
    }
    console.log(data);

    fetch("/api/create_booking", {
        method: 'post',
        body: JSON.stringify({
            id_phong: document.getElementById("id-phong-pt").value,
            ten_phong: document.getElementById("ten-phong-pt").value,
            check_in: document.getElementById("check-in-pt").value,
            check_out: document.getElementById("check-out-pt").value,
            data: JSON.stringify(data),
            co_nguoi_nuoc_ngoai: document.getElementById("co-nguoi-nuoc-ngoai").checked,
            so_nguoi: document.getElementById("so-nguoi-pt").value
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function(res) {
        console.info(res)
        return res.json()
    }).then(function(data) {
        console.info(data)
        if (data.status_code == 200) {
            alert('Đặt phòng thành công');
            location.reload();
        } else if (data.status_code == 404) {
            alert(data.error);
        }

    })
}

// ----------------- Phòng ---------------------
function openEditPhong(id_phong) {
	
}

function openCreatePhong(id_phong) {
	
}

function saveEditPhong() {
	
}

/*function saveCreatePhong() {
	$( "#room-form" ).submit();
}*/

// ----------------- End Phòng -----------------

// ----------------- Tra cứu phòng -------------
function openCreateBooking() {
	$('#myModal').modal();
}

function createBooking() {
	console.log("aaa");
}

// ----------------- End tra cứu phòng ---------