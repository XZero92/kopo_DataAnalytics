let number ="010-1234-5678";

console.log(number.split('-'));

console.log(isNaN(undefined));

console.log(isNaN(null));

console.log(`parseInt('hello') : ${parseInt('hello')}`);
console.log(`parseInt('30b') : ${parseInt('30b')}`);
console.log(`parseFloat('45.4') : ${parseFloat('45.4')}`);

let num = 5;
console.log(`num.toString() : ${typeof num.toString()}`);
console.log(`num.toString() : ${(3).toString()}`);
console.log(`num.toString(2) : ${(3).toString(2)}`); // 2진수로 표현

console.log(`isNaN('5') : ${isNaN('5')}`);
console.log(`isNaN(3) : ${isNaN(3)}`);
console.log(`isNaN('b3') : ${isNaN('b3')}`);
console.log(`isNaN('3b') : ${isNaN('3b')}`);
console.log(isNaN(undefined)); // true가 출력! 혼란 가중 => Number.isNaN 도입
// ES6에서 추가 도입
console.log(Number.isNaN(undefined)); // false
console.log(Number.isNaN(null)); // false
console.log(Number.isNaN(NaN)); // true

console.log(`Math.round(4.7) : ${Math.round(4.7)}`); // 반올림
console.log(`Math.pow(2, 8) : ${Math.pow(2, 8)}`); // 2의 8승
console.log(`Math.sqrt(64) : ${Math.sqrt(64)}`); // 제곱근
console.log(`Math.abs(-5) : ${Math.abs(-5)}`); // 절대값
console.log(`Math.random() : ${Math.random()}`); // 0~1 사이의 랜덤값

console.log(`Math.random() : ${Math.round(Math.random()*45)+1}`);



    const obj = {
        mycount: 0, // count 초기화 값
        increment: function() {
        this.mycount++;
        },
        get count() {
        this.increment(); // increment 메서드 호출로 _count 증가
        return this.mycount;
        }
        };
        console.log(obj.count); // 1
        console.log(obj.count); // 2
        console.log(obj.count); // 3

        // define property example
const coffee = {
    myType: "latte"
    };
    Object.defineProperty(coffee, "type", {
    writable: false
    });
    coffee.type = "MAXIM";
    console.log(coffee.type);

    files = [
        "abc.png",
        "bcd.jpg",
        "land.jpeg",
        "ddwdw.ppt",
        "final.pptx",
        "itisnicehomework.xlxs",
        "go.usa.go.docx",
        "my._programs.exe",
        ];

        for(let i=0; i < files.length; i++) {
            file = files[i].split('.');
            console.log(file[file.length - 1]);
        }

        for(let i=0; i < files.length; i++) {
            console.log(files[i].split('.').pop());
        }

