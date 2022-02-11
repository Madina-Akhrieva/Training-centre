const rus=document.getElementById('set_rus'),
    eng= document.getElementById('set_eng');
rus.addEventListener('click',()=>{
    document.cookie = "lang=ru_RU";
    location.reload();
})

eng.addEventListener('click',()=>{
    document.cookie = "lang=en_US";
    location.reload();
})
