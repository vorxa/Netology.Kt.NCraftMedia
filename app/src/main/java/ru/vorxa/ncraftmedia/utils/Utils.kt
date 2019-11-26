package ru.vorxa.ncraftmedia.utils

import android.graphics.Color
import android.widget.ImageButton
import android.widget.TextView
import ru.vorxa.ncraftmedia.R
import ru.vorxa.ncraftmedia.dto.Post
import ru.vorxa.ncraftmedia.dto.PostType

fun humanizeTime(seconds: Long): String {
    /*
        0с - 59с "менее минуты назад"
        60с - 119с "минуту назад"
        2мин - 59мин "N минут назад"
        60мин - 119мин "час назад"
        2ч - 23ч "N часов назад"
        1д - 2д "день назад"
        2д - 31д "N дней назад"
        31д - 61д "месяц назад"
        62д - 364д "N месяцев назад"
        365д - 729д "год назад"

        >729д "N лет назад"
    */
    var humanize: String = ""
    var amount = 0L

    when {
        seconds in 0L..59L -> return "менее минуты назад"
        seconds in 60L..119L -> return "минуту назад"
        seconds in 3600L..7199L -> return "час назад"
        seconds in 24 * 3600 until 48 * 3600 -> return "день назад"
        (seconds/(24 * 3600)).toLong() in 31 until 62 -> return "месяц назад"
        (seconds/(24 * 3600)).toLong() in 365 until 730 -> return "год назад"
    }

    when {
        // (>119 : <60*60)         (2-59) минут назад
        seconds in 119 until 60*60 -> {
            amount = (seconds / 60).toLong()
            humanize = ending(
                amount,
                TimeUnits.MINUTE
            )
        }
        // (>2*3600-1 : 23*3600)  (2-23) часов назад
        (seconds/3600).toLong() in 2..23 -> {
            amount = (seconds / 3600).toLong()
            humanize = ending(
                amount,
                TimeUnits.HOUR
            )
        }
        // 26*3600+1..360*24*3600 "N дней назад"
        (seconds/(24 * 3600)).toLong() in 2 until 31 -> {
            amount = (seconds / (3600 * 24)).toLong()
            humanize = ending(
                amount,
                TimeUnits.DAY
            )
        }
        // 26*3600+1..360*24*3600 "N дней назад"
        (seconds/(31 * 24 * 3600)).toLong() in 2 until 12 -> {
            amount = (seconds / (31 * 3600 * 24)).toLong()
            humanize = ending(
                amount,
                TimeUnits.MONTH
            )
        }
        // 26*3600+1..360*24*3600 "N дней назад"
        else -> {
            amount = (seconds / (365 * 3600 * 24)).toLong()
            humanize = ending(
                amount,
                TimeUnits.YEAR
            )
        }
    }

    return "$amount $humanize назад"
}

private fun ending (amount: Long, unit: TimeUnits) : String {
    var decs: Long = 0L
    var endings = emptyList<String>()
    var humanize: String = ""
    endings = when (unit) {
        TimeUnits.MINUTE -> listOf("минуту", "минут", "минуты")
        TimeUnits.HOUR -> listOf("час", "часов", "часа")
        TimeUnits.DAY -> listOf("день", "дней", "дня")
        TimeUnits.MONTH -> listOf("месяц", "месяцев", "месяца")
        TimeUnits.YEAR -> listOf("год", "лет", "года")
    }

    decs = if (amount < 100) amount else "$amount".substring("$amount".length-2,"$amount".length).toLong()
    when {
        decs in 5..19 || (decs % 10)  in 5..9 || (decs % 10) == 0L -> humanize = endings.get(1)
        (decs % 10)  in 2..4 -> humanize = endings.get(2)
        decs == 1L || (decs % 10) == 1L -> humanize = endings.get(0)
    }

    return humanize
}

private enum class TimeUnits {
    MINUTE,
    HOUR,
    DAY,
    MONTH,
    YEAR;
}

fun setButtons(byMe: Boolean, count: Int, textView: TextView, buttonView: ImageButton, type: String) {
    textView.text = if (count > 0) count.toString() else ""
    val image = when(type) {
        "favorite" -> if (byMe) R.drawable.ic_favorite_red_24dp else R.drawable.ic_favorite_gray_24dp
        "comment" -> if (byMe) R.drawable.ic_comment_red_24dp else R.drawable.ic_comment_gray_24dp
        else -> if (byMe) R.drawable.ic_share_red_24dp else R.drawable.ic_share_gray_24dp
    }
    buttonView.setImageResource(image)
    textView.setTextColor(if (byMe) Color.RED else Color.GRAY)
}

fun postsList(): List<Post> {
    val currentTime: Long = System.currentTimeMillis()/1000
    val postEvent = Post(
        1,
        "Почтальон Печкин",
        "The old post office in Ny-Ålesund is not in official use anymore, but it is open and visitors are free to walk in.",
        currentTime - 7000,
        type = PostType.EVENT,
        address = "Ny-Ålesund Post Office",
        location = 78.9237826 to 11.9079864
    )
    val postPost = Post(
        2,
        "Дядя Фёдор",
        "Я ничей. Я сам по себе мальчик. Свой собственный. Я из города приехал.",
        currentTime - 6000,
        type = PostType.POST,
        likedByMe = true,
        commentedByMe = false,
        sharedByMe = false,
        likes = 1,
        comments = 0,
        shares = 0
    )

    val listOfPosts = listOf(
        postEvent,
        postPost,
        Post(3, "Кот Матроскин", "Lorde - Yellow Flicker Beat (cover by Sershen&Zaritskaya)",
            currentTime - 5000,
            type = PostType.VIDEO,
            videoLink = "https://www.youtube.com/watch?v=3Bg_bj8pI5c"),
        Post(4, "Шарик", "Здравствуйте. Возьмите меня к себе жить. Я вам буду все охранять.",
            currentTime - 4000, source = postPost, type = PostType.REPOST),
        Post(5, "Мама", "Как мне все это надоело. Наша квартира мне телевизионнную передачу напоминает. \"Что-где-когда\" называется.",
            currentTime - 4000, type = PostType.AD)
    )

    val listTest = listOf(
        Post(
            2,
            "Дядя Фёдор",
            "Я ничей. Я сам по себе мальчик. Свой собственный. Я из города приехал.",
            currentTime - 6000,
            type = PostType.POST,
            likedByMe = true,
            commentedByMe = true,
            sharedByMe = false,
            likes = 10,
            comments = 5,
            shares = 0
        )
    )

    //return(listOfPosts)
    return(listTest)
}