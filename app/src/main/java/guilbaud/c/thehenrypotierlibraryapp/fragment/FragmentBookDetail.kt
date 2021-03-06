package guilbaud.c.thehenrypotierlibraryapp.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import guilbaud.c.thehenrypotierlibraryapp.view.model.BookViewModel
import guilbaud.c.thehenrypotierlibraryapp.R

class FragmentBookDetail : Fragment() {

    private lateinit var model: BookViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_bookdetails, container, false)

        activity?.let {
            model = ViewModelProviders.of(it).get(BookViewModel::class.java)
            model.selected.observe(this, Observer {
                it?.let { book ->

                    // Building image and adding in view
                    val imageView = view.findViewById<ImageView>(R.id.book_cover)
                    Glide
                        .with(view)
                        .load(book.cover)
                        .into(imageView);

                    // Setting book title and price in view
                    view.findViewById<TextView>(R.id.book_title).text = book.title
                    view.findViewById<TextView>(R.id.book_price).text = book.price + "€"

                    // Creating synopsis text for view
                    var synopsis : String = ""
                    book.synopsis.forEach {
                        synopsis += it + "\n\n"
                    }
                    view.findViewById<TextView>(R.id.book_synopsis).text = synopsis

                }
            })
        }

        return view
    }

}