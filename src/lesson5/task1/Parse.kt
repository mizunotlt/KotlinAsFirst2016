@file:Suppress("UNUSED_PARAMETER")
package lesson5.task1

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {0
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        }
        else {
            println("Прошло секунд с начала суток: $seconds")
        }
    }
    else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку
 */

fun dateStrToDigit(str: String): String{
    val month = listOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа"
            , "сентября", "октября", "ноября", "декабря")
    val parth = str.split(" ")
    if ((parth.size != 3 ) || (parth[1] !in month)){
        return ""
    }
    else
        try {
            val day = parth[0].toInt()
            val mont = month.indexOf(parth[1]) + 1
            val year = parth[2].toInt()
            return String.format("%02d.%02d.%d", day, mont , year)
        }
        catch (e: NumberFormatException){
            return ""
        }
}
/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateDigitToStr(digital: String): String{
    val month = listOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа"
            , "сентября", "октября", "ноября", "декабря")
    val parth = digital.split(".")
    try {
        if ((parth.size != 3 ) || (parth[1].toInt() == 0)){
            return ""
        }
        val day = parth[0].toInt()
        val mont = month[parth[1].toInt() - 1]
        val year = parth[2].toInt()
        return String.format("%d %s %d", day, mont , year)
    }
    catch (e: NumberFormatException){
        return ""
    }
}


/**
 * Сложная
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String{
    val parth = phone.split(' ', '(', ')', '-')
    val library = listOf('+', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0')
    var result = ""
    for (i in parth[0]){
        if (i !in library){
            return ""
        }
    }
    result = parth[0]
    try {
        for (i in 1..parth.size - 1 step 1 )
           if (parth[i] != "") {
               val temp = parth[i].toInt()
               result += parth[i]
           }
    }
    catch (e: NumberFormatException){
        return ""
    }
    return result
}



/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val parth = jumps.split('-', '%', ' ')
    var max = -1
    try{
        for (i in parth){
            if (i != ""){
                val temp = i.toInt()
                if (temp > max)
                    max = temp
            }

        }
    }
    catch (e: NumberFormatException){
        return - 1
    }
    return max
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int{
    val parth = jumps.split(' ')
    var max = -1
    try{
        for (i in 1..parth.size - 1 step 2){

            for (j in parth[i])
               if (j == '+') {
                   val temp = parth[i -1].toInt()
                   if (temp > max)
                       max = temp
               }

        }
    }
    catch (e: NumberFormatException){
        return - 1
    }
    return max

}


/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
//для проверки
fun plusMinus(expression: String): Int {
    val parth = expression.split(" ")
    var result = 0
    var countSymbol = 0 // считает число подряд идущих знаков +-
    var countValue = 0 // считает количесвто подряд идущих чисел
    var sign = 1
    try {
        for (i in parth) {
            if ((i != "+")&&(i != "-")){
                countValue++
                countSymbol = 0
                if (countValue < 2)
                    result += sign * i.toInt()
                else
                    throw IllegalArgumentException()
            }
            if ((i == "+")||(i == "-")){
                countSymbol++
                countValue = 0
                if (countSymbol < 2){
                    if (i == "+")
                        sign = 1
                    else
                        sign = - 1
                }
                else
                    throw IllegalArgumentException()
            }
        }
    }
    catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    }
    return result
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int{
    var result = - 1
    var index = 0
    val parth = str.toLowerCase().split(" ")
    for (i in 0..parth.size -1 step 1) {
        for (j in i + 1..parth.size - 1 step 1)
            if (parth[i] == parth[j]) {
                result = index + i
                break
            }
        index += parth[i].length
    }

    return result
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть положительными
 */
fun mostExpensive(description: String): String {
    var result = ""
    val parth = description.split("; ", " ")
    var max = 0.0
    try{
        for (i in 1..parth.size - 1 step 2 ) {
            val next = parth[i].toDouble()
            if (next < 0)
                return ""
            else
                if (next >= max) {
                    max = next
                    result = parth[i - 1]
                }
        }
    }
    catch(e: NumberFormatException){
        return ""
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */

fun fromRoman(roman: String): Int{
    var result = 0
    val library = listOf('I', 'V', 'X', 'L', 'C', 'D', 'M')
    for (i in 0..roman.length - 1 step 1)
        if (roman[i] !in library)
            return - 1

    var indexRoman = 0
    while (indexRoman != roman.length ){
        when (roman[indexRoman]){
            'I' ->{
                if ( indexRoman != roman.length - 1 ) {
                    when (roman[indexRoman + 1]){
                        'I' ->{
                            result += 1
                            indexRoman++
                        }
                        'X' ->{
                            result += 9
                            indexRoman += 2
                        }
                        'V' ->{
                            result += 4
                            indexRoman += 2
                        }
                        else ->{
                            result += 1
                            indexRoman++
                        }
                    }
                }
                else
                {
                    result += 1
                    indexRoman++
                }

            }
            'V' ->{
                result += 5
                indexRoman++
            }
            'X' ->{
                if ( indexRoman != roman.length - 1 ) {
                    when (roman[indexRoman + 1]){
                        'X' ->{
                            result += 10
                            indexRoman++
                        }
                        'L' ->{
                            result += 40
                            indexRoman += 2
                        }
                        'C' ->{
                            result += 90
                            indexRoman += 2
                        }
                        else ->{
                            result += 10
                            indexRoman++
                        }
                    }
                }
                else
                {
                    result += 10
                    indexRoman++
                }

            }
            'L' ->{
                result += 50
                indexRoman++
            }
            'C' ->{
                if ( indexRoman != roman.length - 1 ) {
                    when(roman[indexRoman + 1]){
                        'M' ->{
                            result += 900
                            indexRoman += 2
                        }
                        'D' ->{
                            result += 400
                            indexRoman += 2
                        }
                        'C' -> {
                            result += 100
                            indexRoman++
                        }
                        else ->{
                            result += 100
                            indexRoman++
                        }

                    }
                }
                else
                {
                    result += 100
                    indexRoman++
                }
            }
            'D' ->{
                result += 500
                indexRoman++
            }
            'M' ->{
                result += 1000
                indexRoman++
            }
        }
    }
    return result
}


/**
 * Сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за следующей командой ']';
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за предыдущей командой '[';
 *	{ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за следующей командой '}';
 *	} - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за предыдущей командой '{';
 * (комбинации [] и {} имитируют циклы)
 *
 * Пробел кодирует пустую команду.
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] { } не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после выполнения всех команд.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 */
fun countBrackets(brackets : Char, str: String): Int{
    var result = 0
    for (i in 0.. str.length - 1 step 1)
        if (str[i] == brackets)
            result++
    return result
}

fun computeDeviceCells(cells: Int, commands: String): List<Int> {
    val cellsResult = mutableListOf<Int>()
    val library = listOf('>', '<', '[', ']', '{', '}', '+', '-', ' ')

    for (i in 0..cells - 1 step 1)
        cellsResult.add(0)

    var indexCommands = 1
    var indexCells = cellsResult.size  / 2

    if (countBrackets(library[2], commands) != countBrackets(library[3], commands))
        throw IllegalArgumentException()

    if (countBrackets(library[4], commands) != countBrackets(library[5], commands))
        throw IllegalArgumentException()

    for (i in 0.. commands.length - 1 step 1)
        if (commands[i] !in library)
            throw IllegalArgumentException()

    while (indexCommands  <= (commands.length )){
        if ((indexCells < 0 ) || (indexCells + 1 > cellsResult.size ))
            throw IllegalStateException()
        else
        {
            when (commands[indexCommands - 1]){
                '>' -> indexCells++
                '<' -> indexCells--
                '+' -> cellsResult[indexCells]++
                '-' ->  cellsResult[indexCells]--
                '[' -> {
                    if (cellsResult[indexCells] == 0) {
                        var indexSquare = indexCommands
                        while (commands[indexSquare - 1] != ']'){
                            indexSquare++
                            indexCommands++
                        }
                    }
                }
                ']' -> {
                    if (commands[indexCommands - 1] == ']'){
                        if (cellsResult[indexCells] != 0) {
                            var indexSquare = indexCommands
                            while (commands[indexSquare - 1] != '[') {
                                indexSquare--
                                indexCommands--
                            }
                        }
                    }
                }
                '{' -> {
                    if (commands[indexCommands - 1] == '{'){
                        if (cellsResult[indexCells] == 0) {
                            var indexSquare = indexCommands
                            while (commands[indexSquare - 1] != '}') {
                                indexSquare++
                                indexCommands++
                            }
                        }
                    }
                }
                '}' -> {
                    if (cellsResult[indexCells] != 0){
                        var indexSquare = indexCommands
                        while (commands[indexSquare - 1] != '{'){
                            indexSquare--
                            indexCommands--
                        }
                    }
                }
            }
            indexCommands++
        }
    }
    if ((indexCells < 0 ) || (indexCells + 1 > cellsResult.size ))
        throw IllegalStateException()
    return cellsResult
}

