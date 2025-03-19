console.log('a' < 'b');
console.log('ba' < 'b');
console.log('1' < '04');
console.log('11' < '1');

console.log('1' < 2n);

console.log("-------")

// result1 = 10 || 100;
// result2 = 0 && 100;
// result3 = null || 100;
// result4 = null && 100;
// username = 'gildong';
// result5 = username || '유저 이름이 없습니다';
// username = undefined;
// result6 = username || '유저 이름이 없습니다';

// console.log(result1);
// console.log(result2);
// console.log(result3);
// console.log(result4);
// console.log(result5);
// console.log(result6);


//  null 병합 연산자
let result1;
let result2 = result1 ?? 100;
console.log(result2);
let result3 = 10;
let result4 = result3 ?? 100;
console.log(result4);
let result5 = null;
let result6 = result5 ?? 100;
console.log(result6);
console.log("------")

console.log(null == undefined);
console.log(false == 0);
console.log(0==[]);
console.log(String([1,2,3]));
console.log('a'+{});
console.log('1'<['2']);
console.log(NaN === NaN);

console.log("------")

console.log([] && 'null' || 'default');
console.log([]||'default');
const obj = {
    valueOf() {
        return 1;
    },
    toString() {
        return 'string';
    }
}
console.log(Number(obj));

// function myRecipe() {
//     console.log("1. 냄비를 가스렌지에 올린다");
//     console.log("2. 물 500 mL를 냄비에 채운다");
//     console.log("3. 가스밸브가 잠겨있는 경우 밸브를 연다")
//     console.log("4. 냄비 면적에 맞게 불을 최대한 올린다");
//     console.log("5. 건더기 스프를 냄비에 넣는다");
//     console.log("6. 물이 끓기 시작하면 면과 분말/액상 스프를 냄비에 넣는다");
//     console.log("7. 면을 넣고 부터 3분을 지나면 불을 끈다");
//     console.log("8. 먹는다");
// }

console.log("------");

const factorial = function doSomething(n) {
    return n <= 1 ? 1 : n * doSomething(n-1);
}
console.log(factorial(5)); // 120

console.log("------");

function greeting (name) {
    return `Hello ${name}`;
}
console.log(greeting()); // Hello undefined
console.log(greeting('kim', 'park')); //Hello kim

console.log("------");

function getUserInfo ({name, age, country}) {
    return `Hello ${name}, age : ${age} , country : ${country}`;
}

const userInfo = {name : 'Lee', age : 31};
console.log(getUserInfo(userInfo));

console.log("------");

function add1(a = 100, b = 200)
{
    console.log(a, b);
    return a + b;
}
add1(10, 20);
add1(10);
add1();
add1(b=300)
add1(undefined,300);

function add2({ a = 100, b = 200 })
{
console.log(a+b);
}
add2({b: 300});

console.log("------");

