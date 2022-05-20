package com.edsoft.framework.base.web;

import com.edsoft.framework.controls.api.ControlFactory;

public class BaseFuncionalidadeWeb {

	public static BasePaginaWeb CurrentPage;
	/**
     * BaseFuncionalidadeWeb Inicialização de fábrica da página de controle personalizada
     * @return Tpage
     */
	public <TPage extends BasePaginaWeb> TPage GetInstance(Class<TPage> page) {
		Object obj = ControlFactory.initElements(DriverContextWeb.Driver, page);
		return page.cast(obj);
	}
}
