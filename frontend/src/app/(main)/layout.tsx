import Footer from "@/components/sections/footer";
import Navigationbar from "@/components/sections/navigation-bar";

export default function MainLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <>
      <Navigationbar />
      {children}
      <Footer />
    </>
  );
}
