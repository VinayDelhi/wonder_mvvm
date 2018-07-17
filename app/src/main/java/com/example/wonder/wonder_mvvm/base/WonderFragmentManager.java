package com.example.wonder.wonder_mvvm.base;

import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.ArrayList;

public class WonderFragmentManager {
    public static final int NO_ANIMATION = -1;
    private final int targetId;
    private final ArrayList<Runnable> capturedEvent = new ArrayList<>();
    private FragmentManager mFragmentManager;
    // initial true;
    private boolean mStateSave = false;

    WonderFragmentManager(FragmentManager fragmentManager) {
        this(fragmentManager, -1);
    }

    WonderFragmentManager(FragmentManager fragmentManager, int targetId) {
        this.mFragmentManager = fragmentManager;
        this.targetId = targetId;
    }

    public WonderBaseFragment getTopFragment() {
        return (WonderBaseFragment) mFragmentManager.findFragmentById(targetId);
    }

    public WonderBaseFragment getFragmentFromId(int id) {
        return (WonderBaseFragment) mFragmentManager.findFragmentById(id);
    }

    public void addToBackStack(WonderBaseFragment fragment, boolean defaultAnimation) {
        if (defaultAnimation) {
            addToBackStack(fragment);
        } else {
            replace(targetId, fragment, 0, 0, 0, 0, true);
        }
    }

    public void addToBackStack(WonderBaseFragment fragment) {
        addToBackStack(targetId, fragment);
    }

    public void addToBackStack(WonderBaseFragment fragment, View... sharedTransitionList) {
        addToBackStack(targetId, fragment, sharedTransitionList);
    }

    public void addToBackStack(int targetId, WonderBaseFragment fragment) {
        replace(targetId, fragment, NO_ANIMATION, NO_ANIMATION,
                NO_ANIMATION, NO_ANIMATION, null, true);
    }

    public void addToBackStack(int targetId, WonderBaseFragment fragment, View... sharedTransitionList) {
        replace(targetId, fragment, NO_ANIMATION, NO_ANIMATION,
                NO_ANIMATION, NO_ANIMATION, sharedTransitionList, true);
    }

    public void addToBackStack(WonderBaseFragment fragment, int enter, int exit) {
        addToBackStack(targetId, fragment, enter, exit);
    }

    public void addToBackStack(int targetId, WonderBaseFragment fragment, int enter, int exit) {
        replace(targetId, fragment, enter, exit, 0, 0, true);
    }

    public void replace(WonderBaseFragment fragment) {
        replace(targetId, fragment, NO_ANIMATION, NO_ANIMATION, NO_ANIMATION,
                NO_ANIMATION, false);
    }

    public void removeTopAndAddToBackStack(WonderBaseFragment fragment) {
        popBackStack();
        replace(targetId, fragment, NO_ANIMATION, NO_ANIMATION, NO_ANIMATION,
                NO_ANIMATION, true);
    }

    public void replace(int targetId,
                        WonderBaseFragment fragment) {
        // may provide default animation
        replace(targetId, fragment, NO_ANIMATION,
                NO_ANIMATION, NO_ANIMATION, NO_ANIMATION, false);
    }

    public void replace(int targetId,
                        WonderBaseFragment fragment, boolean defaultAnimation) {
        // may provide default animation
        if (defaultAnimation) {
            replace(targetId, fragment);
        } else {
            replace(targetId, fragment, 0, 0, 0, 0, false);
        }
    }

    public void popBackStack() {
        mFragmentManager.popBackStack();
    }

    public boolean popBackStackImmediate() {
        return mFragmentManager.popBackStackImmediate();
    }


    public void popToHome() {
        if (mFragmentManager != null) {
            //  ArrayList<Fragment> stackList = (ArrayList<Fragment>) mFragmentManager.getFragments();
            try {
                //for (int i = stackList.size() - 1; i > 1; i--) {
                for (int i = 0; i < mFragmentManager.getBackStackEntryCount(); i++) {
                    mFragmentManager.popBackStack();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void popToFragmentByName(String frg) {
        if (mFragmentManager != null) {
            mFragmentManager.popBackStackImmediate(frg,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void setSaveState(boolean saveState) {
        mStateSave = saveState;
    }

    public ArrayList<Runnable> getCapturedEvent() {
        return capturedEvent;
    }

    public void addFirstFragment(final WonderBaseFragment fragment) {
        addFirstFragment(targetId, fragment);
    }

    public void addFirstFragment(final int targetId, final WonderBaseFragment fragment) {
        int count = mFragmentManager.getBackStackEntryCount();
        for (int i = 0; i < count; i++) {
            mFragmentManager.popBackStackImmediate();
        }

        WonderBaseFragment currentFragment = (WonderBaseFragment) mFragmentManager.findFragmentById(targetId);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        transaction.replace(targetId, fragment, fragment.getClass().getName());
        if (currentFragment != null) {
            transaction.remove(currentFragment);
        }
        transaction.commitAllowingStateLoss();
    }

    public void addOverFirstFragment(final WonderBaseFragment fragment) {
        int count = mFragmentManager.getBackStackEntryCount();
        for (int i = 0; i < count; i++) {
            mFragmentManager.popBackStackImmediate();
        }

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        transaction.replace(targetId, fragment, fragment.getClass().getName());
        transaction.addToBackStack(fragment.getClass().getName());
        transaction.commitAllowingStateLoss();
    }

    public void replace(final int targetId, final WonderBaseFragment fragment,
                        final int enter, final int exit, final int popEnter,
                        final int popExit, final boolean isAddToBackStack) {
        replace(targetId, fragment, enter, exit, popEnter, popExit, null, isAddToBackStack);
    }

    public void replace(final int targetId, final WonderBaseFragment fragment,
                        final int enter, final int exit, final int popEnter,
                        final int popExit, final View[] sharedTransitionViews,
                        final boolean isAddToBackStack) {

        final Runnable runnable = new Runnable() {

            @Override
            public void run() {

                WonderBaseFragment currentFragment = (WonderBaseFragment) mFragmentManager
                        .findFragmentById(targetId);

                FragmentTransaction transaction = mFragmentManager
                        .beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
                if (enter == NO_ANIMATION && exit == NO_ANIMATION
                        && popEnter == NO_ANIMATION && popExit == NO_ANIMATION) {
//                    transaction.setCustomAnimations(R.anim.f_enter,
//                            R.anim.f_exit, R.anim.f_pop_enter,
//                            R.anim.f_pop_exit);

                } else {
                    transaction.setCustomAnimations(enter, exit, popEnter,
                            popExit);
                }

                if (sharedTransitionViews != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    for (View v : sharedTransitionViews) {
                        transaction.addSharedElement(v, v.getTransitionName());
                    }
                }

                try {

                    transaction.replace(targetId, fragment, fragment
                            .getClass().getName());
                    if (isAddToBackStack) {
                        transaction.addToBackStack(fragment.getClass()
                                .getName());
                    } else {
                        if (currentFragment != null) {
                            transaction.remove(currentFragment);
                        }
                    }
                    //transaction.setAllowOptimization(true);
                    transaction.commitAllowingStateLoss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        executeAction(runnable);
    }

    public void popBackStackTo(final FragmentManager manager,
                               final FragmentManager.BackStackEntry entry, final int flag) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                manager.popBackStack(entry.getId(), flag);
            }
        };
        executeAction(runnable);
    }

    public void removeFragmentByTag(final String tag) {
        if (mFragmentManager != null) {
            Fragment fragment = mFragmentManager.findFragmentByTag(tag);
            if (fragment != null) {
                remove(fragment);
            }
        }
    }

    public void remove(final Fragment fragment) {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    mFragmentManager.beginTransaction().remove(fragment).commit();
                } catch (IllegalStateException ex) {
                    if (fragment != null && fragment.getActivity() != null) {
                        mFragmentManager.beginTransaction().remove(fragment)
                                .commitAllowingStateLoss();
                    }
                }
            }
        };
        executeAction(runnable);
    }

    public void removeAllFragments(final int flag) {

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                if (mFragmentManager != null && mFragmentManager.getBackStackEntryCount() > 0) {
                    FragmentManager.BackStackEntry backStackEntry = mFragmentManager
                            .getBackStackEntryAt(0);
                    mFragmentManager.popBackStackImmediate(backStackEntry.getId(), flag);
                }
            }
        };
        executeAction(runnable);
    }


    private void executeAction(Runnable runnable) {
        runnable.run();
    }

    public void executePendingEvents() {
        if (!mStateSave) {
            while (capturedEvent.size() > 0) {
                capturedEvent.remove(0).run();
            }
        }
    }


    public void showDialog(final DialogFragment fragment) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                fragment.show(mFragmentManager, fragment.getTag());
            }
        };
        executeAction(runnable);
    }

    public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener listener) {
        mFragmentManager.addOnBackStackChangedListener(listener);
    }

    public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener listener) {
        mFragmentManager.removeOnBackStackChangedListener(listener);
    }

    public boolean popToFragment(String fragment) {
        return popToFragment(fragment, 0);
    }

    public boolean popToFragment(String fragment, int flag) {
        if (mFragmentManager != null) {
            try {
                return mFragmentManager.popBackStackImmediate(fragment, flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public WonderBaseFragment getFragmentByTag(String tag) {
        return (WonderBaseFragment) mFragmentManager.findFragmentByTag(tag);
    }

    public int getBackStackEntryCount() {
        return mFragmentManager.getBackStackEntryCount();
    }

    public void addToBackStackAndPreserveState(int targetId, WonderBaseFragment fragment) {
        add(targetId, fragment, NO_ANIMATION, NO_ANIMATION,
                NO_ANIMATION, NO_ANIMATION, null, true);
    }


    public void add(final int targetId, final WonderBaseFragment fragment,
                    final int enter, final int exit, final int popEnter,
                    final int popExit, final View[] sharedTransitionViews,
                    final boolean isAddToBackStack) {

        final Runnable runnable = new Runnable() {

            @Override
            public void run() {

                WonderBaseFragment currentFragment = (WonderBaseFragment) mFragmentManager
                        .findFragmentById(targetId);

                FragmentTransaction transaction = mFragmentManager
                        .beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
                if (enter == NO_ANIMATION && exit == NO_ANIMATION
                        && popEnter == NO_ANIMATION && popExit == NO_ANIMATION) {
// transaction.setCustomAnimations(R.anim.f_enter,
// R.anim.f_exit, R.anim.f_pop_enter,
// R.anim.f_pop_exit);

                } else {
                    transaction.setCustomAnimations(enter, exit, popEnter,
                            popExit);
                }

                if (sharedTransitionViews != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    for (View v : sharedTransitionViews) {
                        transaction.addSharedElement(v, v.getTransitionName());
                    }
                }

                try {

                    transaction.add(targetId, fragment, fragment
                            .getClass().getName());
                    if (isAddToBackStack) {
                        transaction.addToBackStack(fragment.getClass()
                                .getName());
                    } else {
                        if (currentFragment != null) {
                            transaction.remove(currentFragment);
                        }
                    }

                    transaction.commitAllowingStateLoss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        executeAction(runnable);
    }

    /* All the methods needed when making

    @Override
    public FragmentTransaction beginTransaction() {
        return mFragmentManager.beginTransaction();
    }

    @Override
    public boolean executePendingTransactions() {
        return mFragmentManager.executePendingTransactions();
    }

    @Override
    public WonderBaseFragment findFragmentById(@IdRes int id) {
        return (WonderBaseFragment) mFragmentManager.findFragmentById(id);
    }

    @Override
    public Fragment findFragmentByTag(String tag) {
        return (WonderBaseFragment) mFragmentManager.findFragmentByTag(tag);
    }

    public void popBackStack() {
        mFragmentManager.popBackStack();
    }

    @Override
    public boolean popBackStackImmediate() {
        return mFragmentManager.popBackStackImmediate();
    }

    @Override
    public void popBackStack(String name, int flags) {
        mFragmentManager.popBackStack(name, flags);
    }

    @Override
    public boolean popBackStackImmediate(String name, int flags) {
        return mFragmentManager.popBackStackImmediate(name, flags);
    }

    @Override
    public void popBackStack(int id, int flags) {
        mFragmentManager.popBackStack(id, flags);
    }

    @Override
    public boolean popBackStackImmediate(int id, int flags) {
        return mFragmentManager.popBackStackImmediate(id, flags);
    }

    @Override
    public int getBackStackEntryCount() {
        return mFragmentManager.getBackStackEntryCount();
    }

    @Override
    public FragmentManager.BackStackEntry getBackStackEntryAt(int index) {
        return mFragmentManager.getBackStackEntryAt(index);
    }



    @Override
    public void putFragment(Bundle bundle, String key, Fragment fragment) {
        mFragmentManager.putFragment(bundle, key, fragment);
    }

    @Override
    public WonderBaseFragment getFragment(Bundle bundle, String key) {
        return (WonderBaseFragment) mFragmentManager.getFragment(bundle, key);
    }

    *//*public ArrayList<WonderBaseFragment> getFragments() {
        return (ArrayList<WonderBaseFragment>) mFragmentManager.getFragments();
    }*//*

    @Override
    public Fragment.SavedState saveFragmentInstanceState(Fragment f) {
        return mFragmentManager.saveFragmentInstanceState(f);
    }

    @Override
    public boolean isDestroyed() {
        return mFragmentManager.isDestroyed();
    }

    @Override
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        mFragmentManager.dump(prefix, fd, writer, args);
    }*/
}