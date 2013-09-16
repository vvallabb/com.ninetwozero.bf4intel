package com.ninetwozero.battlelog.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetwozero.battlelog.R;
import com.ninetwozero.battlelog.datatypes.ListRow;
import com.ninetwozero.battlelog.datatypes.ListRowType;

import java.io.File;
import java.util.List;

import static com.ninetwozero.battlelog.datatypes.ListRowType.HEADING;
import static com.ninetwozero.battlelog.datatypes.ListRowType.SIDE_HEADING;
import static com.ninetwozero.battlelog.datatypes.ListRowType.SIDE_REGULAR;
import static com.ninetwozero.battlelog.datatypes.ListRowType.SIDE_REGULAR_CHILD;

public class ExpandableListRowAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<ListRow> mItems;

    public ExpandableListRowAdapter(final Context context, final List<ListRow> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public int getGroupCount() {
        return mItems == null? 0 : mItems.size();
    }

    @Override
    public int getChildrenCount(final int position) {
        return getGroup(position).getChildCount();
    }

    @Override
    public ListRow getGroup(final int position) {
        return mItems.get(position);
    }

    @Override
    public ListRow getChild(final int position, final int childPosition) {
        return getGroup(position).getChild(childPosition);
    }

    @Override
    public long getGroupId(final int position) {
        return position;
    }

    @Override
    public long getChildId(final int position, final int childPosition) {
        return (position*100)+childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int position, int childPosition) {
       return getGroup(position).getChild(childPosition).getType().isEnabled();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return getGroupCount() == 0;
    }

    @Override
    public long getCombinedChildId(final long position, final long childPosition) {
        return position * 1000 + childPosition * 100;
    }

    @Override
    public long getCombinedGroupId(final long position) {
        return position * 1000;
    }

    @Override
    public View getGroupView(final int position, final boolean isExpanded, View convertView, final ViewGroup viewGroup) {
        final ListRow item = getGroup(position);
        final ListRowType type = item.getType();

        if( convertView == null ) {
            convertView = LayoutInflater.from(mContext).inflate(item.getLayout(), viewGroup, false);
        }

        convertView.setEnabled(type != ListRowType.SIDE_HEADING);

        return populateViewFromItem(convertView, item);
    }

    @Override
    public View getChildView(final int group, final int child, final boolean isLastChild, View convertView, final ViewGroup viewGroup) {
        final ListRow item = getChild(group, child);
        final ListRowType type = item.getType();

        if( convertView == null ) {
            convertView = LayoutInflater.from(mContext).inflate(item.getLayout(), viewGroup, false);
        }

        return populateViewFromItem(convertView, item);
    }

    private View populateViewFromItem(final View view, final ListRow item) {
        final Bundle stringMappings = item.getStringMappings();
        final Bundle drawableMappings = item.getDrawableMappings();
        final ListRowType type = item.getType();
        final boolean isRegular = type == SIDE_REGULAR || type == SIDE_REGULAR_CHILD;
        final boolean isHeading = type == HEADING || type == SIDE_HEADING;

        if( isRegular || isHeading ) {
            ((TextView) view.findViewById(R.id.text1)).setText(item.getTitle());
        } else {
            int resourceId = 0;
            Object drawable = null;
            ImageView imageView = null;

            for( String key : stringMappings.keySet() ) {
                resourceId = Integer.parseInt(key);
                ((TextView) view.findViewById(resourceId)).setText(stringMappings.getString(key));
            }

            for( String key : drawableMappings.keySet() ) {
                drawable = drawableMappings.get(key);
                if( drawable == null ) {
                    continue;
                }
                resourceId = Integer.parseInt(key);
                imageView = (ImageView) view.findViewById(resourceId);

                if( drawable instanceof String ) {
                    final String path = mContext.getExternalFilesDir(null) + "/" + drawable + ".png";
                    final File image = new File(path);

                    if( image.exists() ) {
                        imageView.setImageURI(Uri.fromFile(image));
                    } else {
                        imageView.setImageResource(R.drawable.ic_launcher);
                    }
                } else if( drawable instanceof Integer ) {
                    imageView.setImageResource((Integer) drawable);
                }
            }
        }
        return view;
    }
}