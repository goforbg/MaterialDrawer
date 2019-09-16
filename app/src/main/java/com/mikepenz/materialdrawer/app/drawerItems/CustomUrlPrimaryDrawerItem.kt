package com.mikepenz.materialdrawer.app.drawerItems

import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.mikepenz.materialdrawer.app.R
import com.mikepenz.materialdrawer.holder.BadgeStyle
import com.mikepenz.materialdrawer.holder.StringHolder
import com.mikepenz.materialdrawer.model.interfaces.ColorfulBadgeable

/**
 * Created by mikepenz on 03.02.15.
 */
class CustomUrlPrimaryDrawerItem : CustomUrlBasePrimaryDrawerItem<CustomUrlPrimaryDrawerItem, CustomUrlPrimaryDrawerItem.ViewHolder>(), ColorfulBadgeable<CustomUrlPrimaryDrawerItem> {
    override var badge: StringHolder? = null
    override var badgeStyle: BadgeStyle? = BadgeStyle()

    override val type: Int
        get() = R.id.material_drawer_item_custom_url_item

    override val layoutRes: Int
        @LayoutRes
        get() = R.layout.material_drawer_item_primary

    override fun withBadge(badge: StringHolder?): CustomUrlPrimaryDrawerItem {
        this.badge = badge
        return this
    }

    override fun withBadge(badge: String): CustomUrlPrimaryDrawerItem {
        this.badge = StringHolder(badge)
        return this
    }

    override fun withBadge(@StringRes badgeRes: Int): CustomUrlPrimaryDrawerItem {
        this.badge = StringHolder(badgeRes)
        return this
    }

    override fun withBadgeStyle(badgeStyle: BadgeStyle?): CustomUrlPrimaryDrawerItem {
        this.badgeStyle = badgeStyle
        return this
    }

    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)

        val ctx = holder.itemView.context

        //bind the basic view parts
        bindViewHelper(holder)

        //set the text for the badge or hide
        val badgeVisible = StringHolder.applyToOrHide(badge, holder.badge)
        //style the badge if it is visible
        if (badgeVisible) {
            badgeStyle!!.style(holder.badge, getColor(ctx))
            holder.badgeContainer.visibility = View.VISIBLE
        } else {
            holder.badgeContainer.visibility = View.GONE
        }

        //define the typeface for our textViews
        if (typeface != null) {
            holder.badge.typeface = typeface
        }

        //call the onPostBindView method to trigger post bind view actions (like the listener to modify the item if required)
        onPostBindView(this, holder.itemView)
    }

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : CustomBaseViewHolder(view) {
        internal val badgeContainer: View = view.findViewById(R.id.material_drawer_badge_container)
        internal val badge: TextView = view.findViewById<TextView>(R.id.material_drawer_badge)
    }
}
