package com.example.dreamtale.common.view;

import com.example.dreamtale.base.BaseView;
import com.example.dreamtale.common.presenter.PackagePaymentPresenter;
import com.example.dreamtale.network.dto.Package;

import java.util.List;

public interface PackagePaymentView extends BaseView<PackagePaymentPresenter> {
    void loadData(List<Package> data);
}
