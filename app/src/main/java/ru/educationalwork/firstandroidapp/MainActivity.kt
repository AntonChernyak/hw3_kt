package ru.educationalwork.firstandroidapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val post = Post(
            1,
            1586829722,
            "Jack Sparrow",
            "Описание лень придумывать. Пусть будет пока так",
            like = true,
            likeCounter = 100,
            comment = false,
            commentCounter = 0,
            share = true,
            shareCounter = 10
        )

        authorTextView.text = post.author
        contentTextView.text = post.content

        action(likeImageView, likeCounterTextView, post.like, post.likeCounter)
        action(commentImageView, commentsCounterTextView, post.comment, post.commentCounter)
        action(shareImageView, shareTextViewCounter, post.share, post.shareCounter)

        calculateTime(post)
    }

    private fun action(image: ImageView, text: TextView, actionFlag: Boolean, counter: Int) {
        var varCounter = counter
        var varActionFlag = actionFlag
        // Задание начальных параметров
        if (varActionFlag) image.setColorFilter(ContextCompat.getColor(this, R.color.colorLikeRepostShareActionOn))
        text.text = varCounter.toString()

        // Если счётчик = 0, то скрываем поле
        if (varCounter == 0) text.visibility = View.INVISIBLE

        // обработка нажатия
        image.setOnClickListener {
            if (!varActionFlag) {
                // если статус отрицательный, то при клике красим картинку и текст в красный
                image.setColorFilter(ContextCompat.getColor(this, R.color.colorLikeRepostShareActionOn))
                text.setTextColor(ContextCompat.getColor(this, R.color.colorLikeRepostShareActionOn))
                // увеличиваем счётчик на 1
                varCounter += 1
                text.text = varCounter.toString()
                // если счётчик стал > 0, то делаем поле видимым
                if (varCounter > 0) text.visibility = View.VISIBLE
            } else {
                image.setColorFilter(ContextCompat.getColor(this, R.color.colorLikeRepostShareActionOff))
                text.setTextColor(ContextCompat.getColor(this, R.color.colorLikeRepostShareActionOff))
                varCounter -= 1
                text.text = varCounter.toString()
                if (varCounter == 0) text.visibility = View.INVISIBLE
            }
            // переключаем статус
            varActionFlag = !varActionFlag
        }
    }

    private fun calculateTime(post: Post) {
        val time: Long = System.currentTimeMillis() / 1000 - post.date
        val systemPlural: String

        systemPlural = when (time) {
            0L -> getString(R.string.just_now)
            in 1..59 -> this.resources.getQuantityString(R.plurals.plurals_sec, time.toInt(), time)
            in 60..3599 -> this.resources.getQuantityString(R.plurals.plurals_min, time.toInt() / 60, time / 60)
            in 3600..86399 -> this.resources.getQuantityString(R.plurals.plurals_hour, time.toInt() / 3600, time / 3600)
            in 86400..172799 -> getString(R.string.one_day)
            in 172_800..2_678_399 -> getString(R.string.few_days)
            in 2_678_400..5_270_399 -> getString(R.string.month)
            in 5_270_400..31_535_999 -> getString(R.string.few_months)
            in 31_536_000..63_071_999 -> getString(R.string.year)
            else -> getString(R.string.few_years)
        }

        if (time == 0L) timeTextView.text = systemPlural
        else timeTextView.text = resources.getString(R.string.time_string, systemPlural)
    }

}