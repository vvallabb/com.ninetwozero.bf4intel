package com.ninetwozero.bf4intel.ui.unlocks;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ninetwozero.bf4intel.R;
import com.ninetwozero.bf4intel.base.adapter.BaseExpandableIntelAdapter;
import com.ninetwozero.bf4intel.json.unlocks.UnlockCriteria;
import com.ninetwozero.bf4intel.resources.maps.UnlockCriteriaStringMap;
import com.ninetwozero.bf4intel.resources.maps.assignments.AssignmentStringMap;

import java.util.List;
import java.util.Map;

public abstract class BaseUnlockAdapter<T> extends BaseExpandableIntelAdapter<T> {
    public BaseUnlockAdapter(final Map<String, List<T>> itemMap, final Context context) {
        super(itemMap, context);
    }

    @Override
    public View getGroupView(final int position, final boolean isExpanded, View convertView, final ViewGroup viewGroup) {
        final String title = getGroup(position);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_unlocks_heading, viewGroup, false);
        }
        setText(convertView, R.id.category_title, getCategoryString(title));
        return convertView;
    }

    @Override
    public View getChildView(final int group, final int child, final boolean isLastChild, final View convertView, final ViewGroup viewGroup) {
        throw new UnsupportedOperationException(
            "You need to implement getChildView(...) in " + getClass().getSimpleName()
        );
    }

    protected String resolveCriteriaLabel(final UnlockCriteria criteria) {
        if (criteria.isScoreCriteria()) {
            final int resource = UnlockCriteriaStringMap.get(criteria.getLabel());
            final int currentValue = criteria.getCurrentValue();
            final int targetValue = criteria.getTargetValue();
            return String.format(
                    context.getString(resource),
                    String.format("%,d", currentValue < targetValue ? currentValue : targetValue),
                    String.format("%,d", targetValue)
            );
        } else {
            final String label = criteria.getLabel();
            final String labelPrefix = label.substring(0, label.lastIndexOf("_"));
            if (labelPrefix.contains("as")) {
                return String.format(
                        context.getString(R.string.unlock_complete_as),
                        context.getString(AssignmentStringMap.get(criteria.getAward().getName()))
                );
            }
        }
        return criteria.getLabel();
    }

    protected abstract int getCategoryString(final String key);
}