const stars = document.getElementById("product-stars")

function starGenerator(num) {
    let star = "";
    if(num % 1 === 0) {
        for(let i = 0; i < num; i++) {
            star += `<i class="fas fa-star"></i>`
        }
    } else {
        const floorNum = Math.floor(num);
        for(let i = 0; i < floorNum; i++) {
            star += `<i class="fas fa-star"></i>`
        }
        star += `<i class="fas fa-star-half-alt"></i>`
    }
    return star;
}

stars.innerHTML = starGenerator(Number(stars.innerText))

