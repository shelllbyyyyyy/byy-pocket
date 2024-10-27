import Footer from "@/components/sections/footer";
import Navigationbar from "@/components/sections/navigation-bar";

export default function AuthenticatedLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return <>{children}</>;
}
